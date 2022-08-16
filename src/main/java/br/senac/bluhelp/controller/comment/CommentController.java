package br.senac.bluhelp.controller.comment;

import java.util.List;

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

import br.senac.bluhelp.dto.comment.CommentDTO;
import br.senac.bluhelp.projection.address.AddressProjection;
import br.senac.bluhelp.projection.comment.CommentProjection;
import br.senac.bluhelp.service.comment.CommentService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/comment")
public class CommentController {

	private final CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping
	public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(CommentService.save(commentDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateComment(@RequestBody CommentDTO commentDTO, @PathVariable(value = "id") Long id) {
		commentService.update(commentDTO, id);
		return ResponseEntity.status(HttpStatus.OK).body("Comentário atualizado");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "id") Long id) {
		commentService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Comentário deletado");
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommentProjection> getComment(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(commentService.findById(id));
	}
	
	@GetMapping()
	public ResponseEntity<List<AddressProjection>> getAllAddresses() {
		return ResponseEntity.status(HttpStatus.OK).body(CommentService.findAll());
	}
}
