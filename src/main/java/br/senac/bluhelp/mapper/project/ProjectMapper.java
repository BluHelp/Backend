package src.main.java.br.senac.bluhelp.mapper.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import main.java.br.senac.bluhelp.dto.address.AddressDTO;
import main.java.br.senac.bluhelp.dto.project.ProjectDTO;
import main.java.br.senac.bluhelp.model.project.Project;

@Service
public class ProjectMapper {

	public ProjectDTO toDTO(Project project) {

		return new ProjectDTO(project.getId(), project.getCreator(), project.getTitle(), project.getObjective(),
				project.getAddress(), project.getProjectDescription(), project.getProgress());
	}

	public List<ProjectDTO> toDTO(List<Project> contributors) {

		final List<ProjectDTO> contributorsDTO = new ArrayList<>();

		for (Project project : contributors)
			contributorsDTO.add(toDTO(project));

		return projectDTO;

	}

	public Project toEntity(ProjectDTO dto) {
		return new Project(dto.id(), dto.creator(), dto.title(), dto.objective(), dto.address(), dto.projectDescription(),
	}
}