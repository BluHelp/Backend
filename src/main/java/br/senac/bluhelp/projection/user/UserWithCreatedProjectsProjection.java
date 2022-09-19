package br.senac.bluhelp.projection.user;

import java.util.List;

public interface UserWithCreatedProjectsProjection {
	
	Long getId();
	
	String getName();
	
	String getSurname();
	
	List<ProjectProjection> getCreatedProjects();
	
	interface ProjectProjection {
		
		Long getId();
		
		String getTitle();
		
		byte getProgress();
		
		byte getAverageReview();
		
	}

}
