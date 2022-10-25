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
				project.getDescription(), project.getCategory().getId(), project.getAddress().getId(),
				project.getAddress().getStreet(), project.getAddress().getNumber(), 
				project.getAddress().getDistrict(), project.getAddress().getCep(), project.getAddress().getReference());
	}

	public List<ProjectDTO> toDTO(List<Project> contributors) {

		final List<ProjectDTO> contributorsDTO = new ArrayList<>();

		for (Project project : contributors)
			contributorsDTO.add(toDTO(project));

		return contributorsDTO;

	}

}