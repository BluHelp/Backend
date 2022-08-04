package br.senac.bluhelp.service.review;

import br.senac.bluhelp.dto.review.ReviewDTO;

public interface ReviewService {

	public ReviewDTO save(ReviewDTO review);

	public void edit(Long id, ReviewDTO reviewDTO);

	public void delete(Long id);
}
