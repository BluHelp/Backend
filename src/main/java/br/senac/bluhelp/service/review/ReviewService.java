package br.senac.bluhelp.service.review;

import java.util.List;

import br.senac.bluhelp.dto.review.ReviewDTO;
import br.senac.bluhelp.projection.review.ReviewProjection;

public interface ReviewService {

	public ReviewDTO save(ReviewDTO reviewDTO);

	public void update(Long id, ReviewDTO reviewDTO);

	public void delete(Long id);
	
	ReviewProjection findById(Long id);
	
	List<ReviewProjection> findAll();

}
