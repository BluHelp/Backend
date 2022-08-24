package br.senac.bluhelp.projection.project;

import java.time.LocalDateTime;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.user.User;

public interface ProjectWithReviewsProjection{

	Long getId ();
	User getCreator();
	String getTitle();
	Progress getProgress();
	
	interface ProjectReviewsProjection{
			
		Long getId();
		byte getRating();
		User getUser();
		LocalDateTime getDate();
			
			interface UserReviewProjectProjection{
				
				Long getId();
				String getName();
				String getSurname();
				
			}
		}
	}
