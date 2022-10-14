package br.senac.bluhelp.controller.user;

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
import org.springframework.web.bind.annotation.RestController;

import br.senac.bluhelp.dto.user.UserDTO;
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

}
