package br.senac.bluhelp.service.project;

import java.util.List;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectWithAddressProjection;
import br.senac.bluhelp.projection.project.ProjectWithReviewsProjection;

public interface ProjectService {

	ProjectDTO save(ProjectDTO projectDTO);
	void update(Long id, ProjectDTO projectDTO);
	void delete(Long id);
	ProjectProjection findById(Long id);
	ProjectWithAddressProjection findByProjectWithAddressProjection(Long id);
	ProjectWithReviewsProjection findByWithReviewsProjection(Long id);
	List<ProjectProjection> findAll();
	
}
