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
	
	@Query(value ="SELECT r.id AS id, r.rating AS rating, r.project AS project, r.user AS user, r.date AS date FROM Review as r")
	List<ReviewProjection> findReviews();
	
}