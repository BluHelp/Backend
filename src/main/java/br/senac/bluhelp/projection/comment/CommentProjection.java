package br.senac.bluhelp.projection.comment;

import java.time.LocalDateTime;

public interface CommentProjection {

	Long getId();
	
	String getContent();
	
	UserProjection getUser();
	
	interface UserProjection {
		
		Long getId();
		
		String getName();
		
		String getSurname();
		
		byte[] getPhoto();
		
	}
	
	LocalDateTime getDate();
	
}
