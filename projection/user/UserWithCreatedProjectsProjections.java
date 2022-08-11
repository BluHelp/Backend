package br.senac.bluhelp.projection.user;

import java.util.List;

import br.senac.bluhelp.enumeration.category.Category;
import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.project.Project;

public interface UserWithCreatedProjectsProjections {

	Long getId();
	String getName();
	List<Project> getCreatedProjects();
	
	interface ProjectProjection{
		
		Long getId();
		String getTitle();
		String getObjective();
		Progress getProgress();
		Category getCategory();
		
	}
}