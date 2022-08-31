package br.senac.bluhelp.service.review;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.review.ReviewDTO;
import br.senac.bluhelp.exception.project.ProjectNotFoundException;
import br.senac.bluhelp.exception.review.ReviewNotFoundException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.mapper.review.ReviewMapper;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.review.Review;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.projection.review.ReviewProjection;
import br.senac.bluhelp.repository.project.ProjectRepository;
import br.senac.bluhelp.repository.review.ReviewRepository;
import br.senac.bluhelp.repository.user.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;
	private final ReviewMapper reviewMapper;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;

	public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper,
			UserRepository userRepository, ProjectRepository projectRepository) {
		this.reviewRepository = reviewRepository;
		this.reviewMapper = reviewMapper;
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
	}

	public ReviewDTO save(ReviewDTO reviewDTO) {

		Review review = reviewMapper.toEntity(reviewDTO);
		Review reviewSaved = reviewRepository.save(review);

		return reviewMapper.toDTO(reviewSaved);
	}

	public void update(Long id, ReviewDTO reviewDTO) {

		Review review = reviewRepository.findById(id)
				.orElseThrow(() -> new ReviewNotFoundException("Review " + id + " was not found"));

		User user = userRepository.findById(reviewDTO.user())
				.orElseThrow(() -> new UserNotFoundException("User " + reviewDTO.user() + " was not found"));

		Project project = projectRepository.findById(reviewDTO.project())
				.orElseThrow(() -> new ProjectNotFoundException("Project " + reviewDTO.project() + " was not found"));

		review.setRating(reviewDTO.rating());
		review.setUser(user);
		review.setProject(project);
		review.setDate(reviewDTO.date());

		reviewRepository.save(review);
	}

	public void delete(Long id) {

		if (!reviewRepository.existsById(id))
			throw new ReviewNotFoundException("Review " + id + " was not found");

		reviewRepository.deleteById(id);
	}

	public ReviewProjection findById(Long id) {

		ReviewProjection review = reviewRepository.findReviewById(id)
				.orElseThrow(() -> new ReviewNotFoundException("Review " + id + " was not found"));

		return review;
	}

	public List<ReviewProjection> findAll() {

		return reviewRepository.findReviews();
	}

}
