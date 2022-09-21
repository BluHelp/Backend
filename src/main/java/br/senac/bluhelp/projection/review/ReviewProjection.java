package br.senac.bluhelp.projection.review;

import java.time.LocalDateTime;

public interface ReviewProjection {
	
	Long getId();
	
	byte getRating();
	
	UserProjection getUser();
	
	interface UserProjection {
		
		Long getId();
		
		String getName();
		
		String getSurname();
		
		byte[] getPhoto();
		
	}
	
	LocalDateTime getDate();
		
}
