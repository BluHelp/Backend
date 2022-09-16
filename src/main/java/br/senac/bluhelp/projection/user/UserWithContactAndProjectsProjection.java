package br.senac.bluhelp.projection.user;

import java.util.List;

public interface UserWithContactAndProjectsProjection {
	
	Long getId();
	
	String getName();
	
	String getSurname();
	
	String getCpf();
	
	ContactProjection getContact();

	interface ContactProjection {
		
		Long getId();
		
		String getEmail();
		
		String getPhone();	
		
	}
	
	List<ProjectProjection> getCreatedProjects();
	
	List<ProjectProjection> getContributedProjects();
	
	interface ProjectProjection {
		
		Long getId();
		
		String getTitle();
		
		byte getProgress();
		
	}


}
