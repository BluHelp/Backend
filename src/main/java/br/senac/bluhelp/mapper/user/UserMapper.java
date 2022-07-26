package br.senac.bluhelp.mapper.user;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.user.UserDTO;
import br.senac.bluhelp.model.contact.Contact;
import br.senac.bluhelp.model.user.User;

@Service
public class UserMapper {
	
	public UserDTO toDTO(User user, Contact contact) {
		return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getPassword(), user.getCpf(), user.getPhoto(), contact.getPhone(), contact.getEmail());
	}

	public User toEntity(UserDTO userDTO) {
		return new User(userDTO.id(), userDTO.name(), userDTO.surname(), userDTO.password(), userDTO.cpf(), userDTO.photo());
	}

}
