package br.senac.bluhelp.controller.review;

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

import br.senac.bluhelp.dto.review.ReviewDTO;
import br.senac.bluhelp.projection.review.ReviewProjection;
import br.senac.bluhelp.service.review.ReviewService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/review")
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
			this.reviewService = reviewService;
		}

	@PostMapping
	public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewDTO reviewDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ReviewService.save(reviewDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateReview(@RequestBody ReviewDTO reviewDTO,
			@PathVariable(value = "id") Long id) {
		reviewService.update(reviewDTO, id);
		return ResponseEntity.status(HttpStatus.OK).body("Avaliação atualizada");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable(value = "id") Long id) {
		reviewService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("avaliação deletada");
	}

	@GetMapping()
	public ResponseEntity<List<ReviewProjection>> getAllReviews() {
		return ResponseEntity.status(HttpStatus.OK).body(reviewService.findAll());
	}
}
