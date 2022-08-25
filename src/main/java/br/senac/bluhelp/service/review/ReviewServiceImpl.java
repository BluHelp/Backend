package br.senac.bluhelp.service.review;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.review.ReviewDTO;
import br.senac.bluhelp.exception.review.ReviewNotFoundException;
import br.senac.bluhelp.mapper.review.ReviewMapper;
import br.senac.bluhelp.model.review.Review;
import br.senac.bluhelp.projection.review.ReviewProjection;
import br.senac.bluhelp.repository.review.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private final ReviewRepository reviewRepository;
	private final ReviewMapper reviewMapper;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
		this.reviewRepository = reviewRepository;
		this.reviewMapper = reviewMapper;
	}

	public ReviewDTO save(ReviewDTO reviewDTO) {
		
		Review review = reviewMapper.toEntity(reviewDTO);
		Review reviewSaved = reviewRepository.save(review);
		
		return reviewMapper.toDTO(reviewSaved);
	}

	public void update(Long id, ReviewDTO reviewDTO) {
		
		Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review " + id + " was not found"));
		
		review.setRating(reviewDTO.rating());
		review.setUser(reviewDTO.user());
		review.setProject(reviewDTO.project());
		review.setDate(reviewDTO.date());
		
		reviewRepository.save(review);
	}

	public void delete(Long id) {
		
		if(!reviewRepository.existsById(id))
			throw new ReviewNotFoundException("Review " + id + " was not found");
		
		reviewRepository.deleteById(id);
	}

	public ReviewProjection findById(Long id) {
		
		ReviewProjection review = reviewRepository.findReviewById(id).orElseThrow(() -> new ReviewNotFoundException("Review " + id + " was not found"));

		return review;
	}

	public List<ReviewProjection> findAll() {

		return reviewRepository.findReviews();
	}

}
