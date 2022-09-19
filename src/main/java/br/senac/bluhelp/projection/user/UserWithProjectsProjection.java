package br.senac.bluhelp.projection.user;

import java.util.List;

import br.senac.bluhelp.projection.project.ProjectWithProgressProjection;

public interface UserWithProjectsProjection {
	
	Long getId();
	
	String getName();
	
	String getSurname();
	
	String getCpf();

	List<ProjectWithProgressProjection> getCreatedProjects();
	
	List<ProjectWithProgressProjection> getContributedProjects();
	
}
