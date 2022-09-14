package br.senac.bluhelp.projection.user;

import java.util.List;

import br.senac.bluhelp.enumeration.progress.Progress;

public interface UserWithProjectProjection {
	
	Long getId();
	
	String getName();
	
	String getSurname();
	
	List<ProjectProjection> getCreatedProjects();
	
	interface ProjectProjection{
		
		Long getId();
		
		String getTitle();
		
		Progress getProgress();
		
	}

}
