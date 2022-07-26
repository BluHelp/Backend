package br.senac.bluhelp.controller.user;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.senac.bluhelp.dto.user.UserDTO;
import br.senac.bluhelp.dto.user.UserLoginDTO;
import br.senac.bluhelp.dto.user.UserPhotoDTO;
import br.senac.bluhelp.dto.user.UserProfileDTO;
import br.senac.bluhelp.projection.user.UserProjection;
import br.senac.bluhelp.service.user.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

	@Autowired
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO, @PathVariable(value = "id") Long id) {
		userService.update(id, userDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long id) {
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado");
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserProfileDTO> getUserWithProjects(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByIdWithProjects(id));
	}

	@GetMapping()
	public ResponseEntity<List<UserProjection>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@PostMapping("/image/{id}")
	public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Long id) throws IOException{
		userService.savePhoto(file, id);
		return ResponseEntity.status(HttpStatus.CREATED).body("Foto do usuario cadastrada");
	}
	
	@GetMapping("/getImage/{id}")
    public ResponseEntity<UserPhotoDTO> getImage(@PathVariable Long id) {        
        return ResponseEntity.status(HttpStatus.OK).body(userService.getImageById(id));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Long> userLogin(@RequestBody UserLoginDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(dto));
	}

}
