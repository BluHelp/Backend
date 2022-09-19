package br.senac.bluhelp.projection.category;

import java.util.List;

import br.senac.bluhelp.projection.project.ProjectWithProgressProjection;

public interface CategoryWithProjectsProjection {
	
	Long getId();
	
	String getName();
	
	List<ProjectWithProgressProjection> getProjects();

}
