package br.senac.bluhelp.service.user;

import java.util.List;

import br.senac.bluhelp.dto.user.UserDTO;
import br.senac.bluhelp.dto.user.UserProfileDTO;
import br.senac.bluhelp.projection.user.UserProjection;

public interface UserService {

	UserDTO save(UserDTO user);

	void update(Long id, UserDTO userDTO);

	void delete(Long id);

	UserProfileDTO findByIdWithProjects(Long id);

	List<UserProjection> findAll();

}
