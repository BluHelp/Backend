package br.senac.bluhelp.projection.project;

import java.time.LocalDateTime;
import java.util.List;

import br.senac.bluhelp.model.review.Review;
import br.senac.bluhelp.model.user.User;

public interface ProjectWithReviewsProjection{

	Long getId();
	User getCreator();
	String getTitle();
	String getObjective();
	List<Review> getReview();
	
	interface ProjectCreator{
		
		Long getId();
		String getName();
		
	}
	
	interface ProjectReviewsProjection{
			
		Long getId();
		byte getNote();
		User getUser();
		LocalDateTime getDate();
			
			interface UserReviewProjectProjection{
				
				Long getId();
				String getName();
				
			}
		}
	}
