package br.senac.bluhelp.mapper.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.model.project.Project;

@Service
public class ProjectMapper {

	public ProjectDTO toDTO(Project project) {

		return new ProjectDTO(project.getId(), project.getCreator(), project.getTitle(), project.getObjective(),
				project.getAddress(), project.getProjectDescription(), project.getProgress(), project.getCategory());
	}

	public List<ProjectDTO> toDTO(List<Project> contributors) {

		final List<ProjectDTO> contributorsDTO = new ArrayList<>();

		for (Project project : contributors)
			contributorsDTO.add(toDTO(project));

		return contributorsDTO;

	}

	public Project toEntity(ProjectDTO projectDTO) {
		return new Project(projectDTO.id(), projectDTO.creator(), projectDTO.title(), projectDTO.objective(),
				projectDTO.address(), projectDTO.projectDescription(), projectDTO.progress(), projectDTO.category());
	}
}