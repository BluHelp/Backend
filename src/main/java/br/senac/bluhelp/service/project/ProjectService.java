package br.senac.bluhelp.service.project;

import java.util.List;

import br.senac.bluhelp.dto.project.ProjectDTO;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.dto.project.ProjectProjectionDTO;
import br.senac.bluhelp.projection.project.ProjectQueryProjection;


public interface ProjectService {

	ProjectDTO save(ProjectDTO projectDTO);

	void update(Long id, ProjectDTO projectDTO);

	void delete(Long id);

	ProjectProjectionDTO findProjectWithAverageReviewById(Long id);

	List<ProjectWithProgressProjection> findByProgress(Progress progress);

	List<ProjectQueryProjection> findAll();

}
