package br.senac.bluhelp.projection.review;

import java.time.LocalDateTime;

import br.senac.bluhelp.model.user.User;

public interface ReviewProjection {
	
	Long getId();
	byte getGrade();
	User getUser();
	LocalDateTime getDate();
		
}
