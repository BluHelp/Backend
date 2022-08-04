package br.senac.bluhelp.service.user;

import br.senac.bluhelp.dto.user.UserDTO;

public interface UserService {

	UserDTO save(UserDTO user);

	void edit(Long id, UserDTO userDTO);

	void delete(Long id);

}
