package br.senac.bluhelp.projection.user;

import java.util.List;

import br.senac.bluhelp.projection.project.ProjectQueryProjection;

public interface UserWithCreatedProjectsProjection {
	
	List<ProjectQueryProjection> getCreatedProjects();

}
