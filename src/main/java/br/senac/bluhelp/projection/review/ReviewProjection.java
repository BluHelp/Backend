package br.senac.bluhelp.projection.review;

import java.time.LocalDateTime;

import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;

public interface ReviewProjection {
	
	Long getId();
	byte getNote();
	User getUser();
	Project getProject();
	LocalDateTime getDate();
		
}
