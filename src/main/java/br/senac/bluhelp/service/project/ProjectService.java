package br.senac.bluhelp.service.project;

import java.util.List;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectWithProgressProjection;

public interface ProjectService {

	ProjectDTO save(ProjectDTO projectDTO);

	void update(Long id, ProjectDTO projectDTO);

	void delete(Long id);

	ProjectProjection findById(Long id);
	
	ProjectWithProgressProjection findByIdWithProgress(Long id);
	
	//byte findByIdWithAverageReview(Long id);

	List<ProjectWithProgressProjection> findAll();

}
