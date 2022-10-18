package br.senac.bluhelp.service.user;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.senac.bluhelp.dto.user.UserDTO;
import br.senac.bluhelp.dto.user.UserLoginDTO;
import br.senac.bluhelp.dto.user.UserPhotoDTO;
import br.senac.bluhelp.dto.user.UserProfileDTO;
import br.senac.bluhelp.projection.user.UserProjection;

public interface UserService {

	UserDTO save(UserDTO user);

	void update(Long id, UserDTO userDTO);

	void delete(Long id);

	UserProfileDTO findByIdWithProjects(Long id);

	List<UserProjection> findAll();
	
	void savePhoto(MultipartFile file, Long id) throws IOException;

	UserPhotoDTO getImageById(Long id);
	
	Long loginUser(UserLoginDTO dto);

}
