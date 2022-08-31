package br.senac.bluhelp.mapper.review;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.review.ReviewDTO;
import br.senac.bluhelp.exception.project.ProjectNotFoundException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.review.Review;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.repository.project.ProjectRepository;
import br.senac.bluhelp.repository.user.UserRepository;

@Service
public class ReviewMapper {

	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;

	public ReviewMapper(UserRepository userRepository, ProjectRepository projectRepository) {
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
	}

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

	public Review toEntity(ReviewDTO reviewDTO) {

		User user = userRepository.findById(reviewDTO.user())
				.orElseThrow(() -> new UserNotFoundException("User " + reviewDTO.user() + " was not found"));

		Project project = projectRepository.findById(reviewDTO.project())
				.orElseThrow(() -> new ProjectNotFoundException("Project " + reviewDTO.project() + " was not found"));

		return new Review(reviewDTO.id(), reviewDTO.rating(), user, project, reviewDTO.date());
	}

}
