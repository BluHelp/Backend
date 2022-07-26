package br.senac.bluhelp.mapper.review;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.review.ReviewDTO;
import br.senac.bluhelp.model.review.Review;

@Service
public class ReviewMapper {

	public ReviewDTO toDTO(Review review) {
		return new ReviewDTO(review.getId(), review.getRating(), review.getUser().getId(), review.getProject().getId(),
				review.getDate());
	}

	public List<ReviewDTO> toDTO(List<Review> reviews) {

		final List<ReviewDTO> reviewsDTO = new ArrayList<>();

		for (Review review : reviews) {
			reviewsDTO.add(toDTO(review));
		}

		return reviewsDTO;
	}

}
