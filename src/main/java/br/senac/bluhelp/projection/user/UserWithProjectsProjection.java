package br.senac.bluhelp.projection.user;

import java.util.List;

import br.senac.bluhelp.enumeration.progress.Progress;

public interface UserWithProjectsProjection {
	
	Long getId();
	
	String getName();
	
	String getSurname();
	
	String getCpf();

	List<ProjectQueryProjection> getCreatedProjects();
	
	List<ProjectQueryProjection> getContributedProjects();
	
	interface ProjectQueryProjection {
		
		Long getId();
		
		String getTitle();
		
		byte[] getPhoto();
		
		Progress getProgress();
		
		Double getAverageReview();
		
	}

}
