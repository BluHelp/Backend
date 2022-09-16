package br.senac.bluhelp.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.user.UserDTO;
import br.senac.bluhelp.exception.user.UserCpfRegisteredException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.mapper.user.UserMapper;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.projection.user.UserProjection;
import br.senac.bluhelp.projection.user.UserWithCreatedProjectsProjection;
import br.senac.bluhelp.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public UserDTO save(UserDTO userDTO) {

		if (userRepository.existsByCpf(userDTO.cpf()))
			throw new UserCpfRegisteredException("CPF " + userDTO.cpf() + " is already registered");

		User user = userMapper.toEntity(userDTO);
		User userSaved = userRepository.save(user);

		return userMapper.toDTO(userSaved);

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

	public UserProjection findById(Long id) {

		UserProjection user = userRepository.findUserById(id)
				.orElseThrow(() -> new UserNotFoundException("User " + id + " was not found"));

		return user;
	}

	public UserWithCreatedProjectsProjection findByIdWithCreatedProjects(Long id) {

		UserWithCreatedProjectsProjection user = userRepository.findUserWithCreatedProjectsById(id)
				.orElseThrow(() -> new UserNotFoundException("User " + id + " was not found"));

		return user;
	}

	public List<UserProjection> findAll() {

		return userRepository.findUsers();
	}
}
