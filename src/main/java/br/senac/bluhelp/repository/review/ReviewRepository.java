package br.senac.bluhelp.repository.review;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.bluhelp.model.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
