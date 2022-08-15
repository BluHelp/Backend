package br.senac.bluhelp.service.user;

import java.util.List;

import br.senac.bluhelp.dto.user.UserDTO;
import br.senac.bluhelp.projection.user.UserProjection;
import br.senac.bluhelp.projection.user.UserWithContactProjection;
import br.senac.bluhelp.projection.user.UserWithContributedProjectsProjection;
import br.senac.bluhelp.projection.user.UserWithCreatedProjectsProjections;

public interface UserService {

	UserDTO save(UserDTO user);

	void update(Long id, UserDTO userDTO);

	void delete(Long id);
	
	UserProjection findById(Long id);
	
	UserWithContactProjection findByIdWithContact(Long id);
	
	UserWithContributedProjectsProjection findByIdWithContributedProjects(Long id);
	
	UserWithCreatedProjectsProjections findByIdWithCreatedProjects(Long id);
	
	List<UserProjection> findAll();

}
