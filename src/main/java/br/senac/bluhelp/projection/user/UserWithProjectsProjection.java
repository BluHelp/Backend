package br.senac.bluhelp.projection.user;

import java.util.List;

import br.senac.bluhelp.projection.project.ProjectWithProgressProjection;

public interface UserWithProjectsProjection {
	
	Long getId();
	
	List<ProjectWithProgressProjection> getCreatedProjects();
	;
	
}
