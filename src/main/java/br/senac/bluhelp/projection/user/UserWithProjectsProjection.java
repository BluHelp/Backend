package br.senac.bluhelp.projection.user;

import java.util.List;

import br.senac.bluhelp.projection.project.ProjectQueryProjection;

public interface UserWithProjectsProjection {
	
	Long getId();
	
	String getName();
	
	String getSurname();
	
	String getCpf();

	List<ProjectQueryProjection> getCreatedProjects();
	
	List<ProjectQueryProjection> getContributedProjects();
	
}
