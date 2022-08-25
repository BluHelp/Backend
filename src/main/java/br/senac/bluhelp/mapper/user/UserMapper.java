package br.senac.bluhelp.mapper.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.user.UserDTO;
import br.senac.bluhelp.model.user.User;

@Service
public class UserMapper {
	
	public UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getPassword(), user.getCpf(), user.getPhoto());
	}
	
	public List<UserDTO> toDTO(List<User> users){
		
		final List<UserDTO> usersDTO = new ArrayList<>();
		
		for (User user : users) {
			usersDTO.add(toDTO(user));
		}
		
		return usersDTO;
	}

	public User toEntity(UserDTO userDTO) {
		return new User(userDTO.id(), userDTO.name(), userDTO.surname(), userDTO.password(), userDTO.cpf(), userDTO.photo());
	}

}
