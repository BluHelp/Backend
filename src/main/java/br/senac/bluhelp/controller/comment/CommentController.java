package br.senac.bluhelp.controller.comment;

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

import br.senac.bluhelp.dto.comment.CommentDTO;
import br.senac.bluhelp.projection.comment.CommentProjection;
import br.senac.bluhelp.service.comment.CommentService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping
	public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(commentDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateComment(@RequestBody CommentDTO commentDTO,
			@PathVariable(value = "id") Long id) {
		commentService.update(id, commentDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Comentário atualizado");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "id") Long id) {
		commentService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Comentário deletado");
	}

	@GetMapping()
	public ResponseEntity<List<CommentProjection>> getAllComments() {
		return ResponseEntity.status(HttpStatus.OK).body(commentService.findAll());
	}
}
