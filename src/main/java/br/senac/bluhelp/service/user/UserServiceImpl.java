package br.senac.bluhelp.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.contact.ContactDTO;
import br.senac.bluhelp.dto.project.ProjectQueryDTO;
import br.senac.bluhelp.dto.user.UserDTO;
import br.senac.bluhelp.dto.user.UserProjectionDTO;
import br.senac.bluhelp.exception.contact.ContactNotFoundException;
import br.senac.bluhelp.exception.user.UserCpfRegisteredException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.mapper.contact.ContactMapper;
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

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final ProjectRepository projectRepository;
	private final ContactRepository contactRepository;
	private final ContactService contactService;
	private final ContactMapper contactMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, ProjectRepository projectRepository, 
			ContactRepository contactRepository, ContactService contactService, ContactMapper contactMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.projectRepository = projectRepository;
		this.contactRepository = contactRepository;
		this.contactService = contactService;
		this.contactMapper = contactMapper;
	}

	public UserDTO save(UserDTO userDTO) {

		if (userRepository.existsByCpf(userDTO.cpf()))
			throw new UserCpfRegisteredException("CPF " + userDTO.cpf() + " is already registered");
	
		User user = userMapper.toEntity(userDTO);
		User userSaved = userRepository.save(user);
		
		ContactDTO contact = new ContactDTO(userDTO.id(), userDTO.email(), userDTO.phone(), userDTO.id());
		
		contactService.save(contact);
		Contact contactSaved = contactMapper.toEntity(contact);

		return userMapper.toDTO(userSaved, contactSaved);

	}

	public void update(Long id, UserDTO userDTO) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User " + id + " was not found"));

		if (!user.getCpf().equals(userDTO.cpf())) {
			if (userRepository.existsByCpf(userDTO.cpf())) {
				throw new UserCpfRegisteredException("CPF " + userDTO.cpf() + " is already registered");
			}
		}

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

		userRepository.deleteById(id);
	}

	public UserProjectionDTO findByIdWithCreatedProjects(Long id) {

		UserProjection user = userRepository.findUserById(id)
				.orElseThrow(() -> new UserNotFoundException("User " + id + " was not found"));
		
		List<ProjectQueryDTO> projects = projectRepository.findCreatedProjectsByUser(id);
		
		ContactProjection contact = contactRepository.findContactById(id).orElseThrow(() -> new ContactNotFoundException("Contact " + id + " was not found"));
		
		UserProjectionDTO dto = new UserProjectionDTO(id, user.getName(), user.getSurname(), user.getCpf(), user.getPhoto(), contact.getPhone(), contact.getEmail(), projects);
		
		return dto;
	}
	
	public UserProjectionDTO findByIdWithContributedProjects(Long id) {
		
		UserProjection user = userRepository.findUserById(id)
				.orElseThrow(() -> new UserNotFoundException("User " + id + " was not found"));
		
		ContactProjection contact = contactRepository.findContactById(id).orElseThrow(() -> new ContactNotFoundException("Contact " + id + " was not found"));
		
		List<ProjectQueryProjection> projects = projectRepository.findContributedProjectsByUser(id);
		
		List<ProjectQueryDTO> projectsDTO = new ArrayList<>();
		
		for (ProjectQueryProjection project : projects) {
			
			Double average = projectRepository.findAverageReviewById(project.getId());
			
			ProjectQueryDTO dto = new ProjectQueryDTO(project.getId(), project.getTitle(), project.getPhoto(), project.getProgress(), average);
			
			projectsDTO.add(dto);
		}
				
		UserProjectionDTO dto = new UserProjectionDTO(id, user.getName(), user.getSurname(), user.getCpf(), user.getPhoto(), contact.getPhone(), contact.getEmail(), projectsDTO);
		
		return dto;
	}

	public List<UserProjection> findAll() {

		return userRepository.findUsers();
	}

	

}
