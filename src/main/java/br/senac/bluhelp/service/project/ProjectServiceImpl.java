package br.senac.bluhelp.service.project;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.exception.project.ProjectNotFoundException;
import br.senac.bluhelp.exception.user.UserCpfRegisteredException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectWithAddressProjection;
import br.senac.bluhelp.projection.project.ProjectWithReviewsProjection;
import br.senac.bluhelp.projection.user.UserProjection;
import br.senac.bluhelp.repository.project.ProjectRepository;
import br.senac.bluhelp.mapper.project.ProjectMapper;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;

	public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
		this.projectRepository = projectRepository;
		this.projectMapper = projectMapper;
	}

	public ProjectDTO save(ProjectDTO projectDTO) {

		Project project = projectMapper.toEntity(projectDTO);
		Project projectSaved = projectRepository.save(project);

		return projectMapper.toDTO(projectSaved);

	}

	public void update(Long id, ProjectDTO projectDTO) {
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));

		project.setAddress(projectDTO.address());
		project.setCreator(projectDTO.creator());
		project.setObjective(projectDTO.objective());
		project.setProgress(projectDTO.progress());
		project.setProjectDescription(projectDTO.projectDescription());
		project.setTitle(projectDTO.title());

		projectRepository.save(project);

	}

	public void delete(Long id) {
		if (!projectRepository.existsById(id)) {
			throw new ProjectNotFoundException("Project " + id + " was not found");
		}

		projectRepository.deleteById(id);
	}

	public ProjectProjection findById(Long id) {
		ProjectProjection project = projectRepository.findProjectById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));

		return project;
	}

	public ProjectWithAddressProjection findByProjectWithAddressProjection(Long id) {
		ProjectWithAddressProjection project = projectRepository.findProjectWithAddressById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));

		return project;
	}

	public ProjectWithReviewsProjection findByWithReviewsProjection(Long id) {
		ProjectWithReviewsProjection project = projectRepository.findProjectWithReviewsById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));

		return project;
	}

	public List<ProjectProjection> findAll() {
		return projectRepository.findProjects();
	}

}