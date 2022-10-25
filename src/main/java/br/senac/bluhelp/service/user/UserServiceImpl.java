package br.senac.bluhelp.service.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.senac.bluhelp.dto.contact.ContactDTO;
import br.senac.bluhelp.dto.project.ProjectQueryDTO;
import br.senac.bluhelp.dto.user.UserDTO;
import br.senac.bluhelp.dto.user.UserLoginDTO;
import br.senac.bluhelp.dto.user.UserPhotoDTO;
import br.senac.bluhelp.dto.user.UserProfileDTO;
import br.senac.bluhelp.exception.contact.ContactNotFoundException;
import br.senac.bluhelp.exception.contact.EmailNotFoundException;
import br.senac.bluhelp.exception.user.UserCpfRegisteredException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.exception.user.UserPasswordException;
import br.senac.bluhelp.mapper.user.UserMapper;
import br.senac.bluhelp.model.contact.Contact;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.projection.contact.ContactProjection;
import br.senac.bluhelp.projection.project.ProjectQueryProjection;
import br.senac.bluhelp.projection.user.UserProjection;
import br.senac.bluhelp.repository.contact.ContactRepository;
import br.senac.bluhelp.repository.project.ProjectRepository;
import br.senac.bluhelp.repository.user.UserRepository;
import br.senac.bluhelp.service.contact.ContactService;
import br.senac.bluhelp.util.ImageUtil;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final ProjectRepository projectRepository;
	private final ContactRepository contactRepository;
	private final ContactService contactService;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, ProjectRepository projectRepository,
			ContactRepository contactRepository, ContactService contactService) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.projectRepository = projectRepository;
		this.contactRepository = contactRepository;
		this.contactService = contactService;
	}

	public UserDTO save(UserDTO userDTO) {

		if (userRepository.existsByCpf(userDTO.cpf()))
			throw new UserCpfRegisteredException("CPF " + userDTO.cpf() + " is already registered");

		User user = userMapper.toEntity(userDTO);
		User userSaved = userRepository.save(user);

		Contact contact = new Contact(userDTO.id(), userDTO.email(), userDTO.phone(), userSaved);

		contactService.save(contact);

		return userMapper.toDTO(userSaved, contact);

	}

	public void update(Long id, UserDTO userDTO) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User " + id + " was not found"));

		if (!user.getCpf().equals(userDTO.cpf())) {
			if (userRepository.existsByCpf(userDTO.cpf())) {
				throw new UserCpfRegisteredException("CPF " + userDTO.cpf() + " is already registered");
			}
		}

		ContactDTO contact = new ContactDTO(id, userDTO.email(), userDTO.phone(), id);

		contactService.update(id, contact);

		user.setName(userDTO.name());
		user.setSurname(userDTO.surname());
		user.setPassword(userDTO.password());
		user.setCpf(userDTO.cpf());
		user.setPhoto(userDTO.photo());

		userRepository.save(user);
	}

	public void delete(Long id) {

		if (!userRepository.existsById(id))
			throw new UserNotFoundException("User " + id + " was not found");

		contactService.delete(id);
		userRepository.deleteById(id);
	}

	public UserProfileDTO findByIdWithProjects(Long id) {

		UserProjection user = userRepository.findUserById(id)
				.orElseThrow(() -> new UserNotFoundException("User " + id + " was not found"));

		ContactProjection contact = contactRepository.findContactById(id)
				.orElseThrow(() -> new ContactNotFoundException("Contact " + id + " was not found"));

		List<ProjectQueryProjection> crProjects = projectRepository.findCreatedProjectsByUser(id);

		List<ProjectQueryProjection> coProjects = projectRepository.findContributedProjectsByUser(id);
		
		List<ProjectQueryDTO> createdProjects = new ArrayList<>();

		List<ProjectQueryDTO> contributedProjects = new ArrayList<>();
		
		for (ProjectQueryProjection project : crProjects) {

			Double average = projectRepository.findAverageReviewById(project.getId());

			ProjectQueryDTO dto = new ProjectQueryDTO(project.getId(), project.getTitle(), project.getPhoto(), project.getProgress(), average);	

			createdProjects.add(dto);
		}

		for (ProjectQueryProjection project : coProjects) {

			Double average = projectRepository.findAverageReviewById(project.getId());

			ProjectQueryDTO dto = new ProjectQueryDTO(project.getId(), project.getTitle(), project.getPhoto(), project.getProgress(), average);	

			contributedProjects.add(dto);
		}

		UserProfileDTO dto = new UserProfileDTO(id, user.getName(), user.getSurname(), user.getCpf(), user.getPhoto(),
				contact.getPhone(), contact.getEmail(), createdProjects, contributedProjects);

		return dto;
	}

	public List<UserProjection> findAll() {

		return userRepository.findUsers();
	}

	public void savePhoto(MultipartFile file, Long id) throws IOException {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User " + id + " was not found"));

		user.setPhoto(ImageUtil.compressBytes(file.getBytes()));

		userRepository.save(user);
	}

	public UserPhotoDTO getImageById(Long id) {
		User dbImage = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User " + id + " was not found"));
		return new UserPhotoDTO(Base64.getEncoder().encodeToString(ImageUtil.decompressBytes(dbImage.getPhoto())));
	}

	public Long loginUser(UserLoginDTO dto) {
		
		if (!contactRepository.existsByEmail(dto.email()))
			throw new EmailNotFoundException("Email " + dto.email() + " was not found");

		ContactProjection contact = contactRepository.findByEmailContainsIgnoreCase(dto.email())
				.orElseThrow(() -> new ContactNotFoundException("User with " + dto.email() + " was not found"));
		UserProjection user = userRepository.findUserById(contact.getId())
				.orElseThrow(() -> new UserNotFoundException("User " + contact.getId() + " was not found"));
		
		if(!dto.password().equals(user.getPassword())) {
            throw new UserPasswordException("Incorrect password");
        }
		
		return user.getId();
	}

}
