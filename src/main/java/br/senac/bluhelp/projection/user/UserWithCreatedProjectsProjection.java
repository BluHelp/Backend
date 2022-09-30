package br.senac.bluhelp.projection.user;

import java.util.List;

import br.senac.bluhelp.projection.project.ProjectProjection;

public interface UserWithCreatedProjectsProjection {
	
	Long getId();
	
	String getName();
	
	String getSurname();
	
	List<ProjectProjection> getCreatedProjects();

}
