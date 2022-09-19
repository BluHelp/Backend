package br.senac.bluhelp.repository.review;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.model.review.Review;

import br.senac.bluhelp.projection.review.ReviewProjection;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	boolean existsById(Long id);
	
	Optional <ReviewProjection> findReviewById(Long id);
	
	@Query(value ="SELECT r.id AS id, r.rating AS rating, r.project AS project, r.user AS user FROM Review as r")
	List<ReviewProjection> findReviews();
	
	@Query(value = "SELECT avg(r.rating) FROM Review as r WHERE r.project = ?1")
	byte getAverageReview(Long project_id);
	
}