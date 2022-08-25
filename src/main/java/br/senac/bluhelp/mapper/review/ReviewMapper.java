package br.senac.bluhelp.mapper.review;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.review.ReviewDTO;
import br.senac.bluhelp.model.review.Review;

@Service
public class ReviewMapper {
	
	public ReviewDTO toDTO(Review review) {
		return new ReviewDTO(review.getId(), review.getRating(), review.getUser(), review.getProject(), review.getDate());
	}
	
	public List<ReviewDTO> toDTO(List<Review> reviews){
		
		final List<ReviewDTO> reviewsDTO = new ArrayList<>();
		
		for(Review review : reviews) {
			reviewsDTO.add(toDTO(review));
		}
		
		return reviewsDTO;
	}
	
	public Review toEntity(ReviewDTO reviewDTO) {
		return new Review(reviewDTO.id(), reviewDTO.rating(), reviewDTO.user(), reviewDTO.project(), reviewDTO.date());
	}
	

}
