package br.senac.bluhelp.mapper.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.model.project.Project;

@Service
public class ProjectMapper {

	public ProjectDTO toDTO(Project project) {

		return new ProjectDTO(project.getId(), project.getCreator().getId(), project.getTitle(), project.getObjective(),
				project.getAddress().getId(), project.getDescription(), project.getProgress().ordinal(), project.getCategory().ordinal(), project.getPhoto());
	}

	public List<ProjectDTO> toDTO(List<Project> contributors) {

		final List<ProjectDTO> contributorsDTO = new ArrayList<>();

		for (Project project : contributors)
			contributorsDTO.add(toDTO(project));

		return contributorsDTO;

	}

	public Project toEntity(ProjectDTO projectDTO) {
		return new Project(projectDTO.id(), projectDTO.creator(), projectDTO.title(), projectDTO.objective(),
				projectDTO.address(), projectDTO.description(), projectDTO.progress(), projectDTO.category(), projectDTO.photo());
	}
}