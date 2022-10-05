package br.senac.bluhelp.service.user;

import java.util.List;

import br.senac.bluhelp.dto.user.UserDTO;
import br.senac.bluhelp.dto.user.UserProjectionDTO;
import br.senac.bluhelp.projection.user.UserProjection;

public interface UserService {

	UserDTO save(UserDTO user);

	void update(Long id, UserDTO userDTO);

	void delete(Long id);

	UserProjectionDTO findByIdWithCreatedProjects(Long id);
	
	UserProjectionDTO findByIdWithContributedProjects(Long id);

	List<UserProjection> findAll();

}
