package br.senac.bluhelp.service.project;

import java.util.List;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectWithProgressProjection;

public interface ProjectService {

	ProjectDTO save(ProjectDTO projectDTO);

	void update(Long id, ProjectDTO projectDTO);

	void delete(Long id);

	ProjectProjection findById(Long id);

	List<ProjectWithProgressProjection> findByProgress(Progress progress);
	
	List<ProjectWithProgressProjection> findByTitle(String title);

	List<ProjectWithProgressProjection> findAll();

}
