package br.senac.bluhelp.projection.project;

import java.time.LocalDateTime;
import java.util.List;

import br.senac.bluhelp.enumeration.category.Category;
import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.comment.Comment;
import br.senac.bluhelp.model.review.Review;
import br.senac.bluhelp.model.user.User;

public interface ProjectProjection {

	Long getId ();
	User getCreator();
	String getTitle();
	String getObjective();
	Address getAddress();
	String getProjectDescription();
	List<User> getContributors();
	List<Comment> getComments();
	List<Review> getReview();
	Progress getProgress();
	Category getCategory();

	
	interface ProjectCreatorProjection{
	
		Long getId();
		String getName();
		
	}
	
	interface ProjectCommentsProjection{
		
		Long getId();
		String getContents();
		User getUser();
		LocalDateTime getDate();
		Comment getReferenceComment();
		
	}
	
	interface ProjectAddressProjection{
		
		Long getId();
		String getRoadType();
		String getStreet();
		Short getNumber();
		String getNeighborhood();
		
	}
	
	interface ProjectReviewProjection{
		
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
