package br.senac.bluhelp.projection.address;

import java.util.List;

import br.senac.bluhelp.projection.project.ProjectProjection;

public interface AddressWithProjectsProjection {

	List<ProjectProjection> getProjects();
	
}
