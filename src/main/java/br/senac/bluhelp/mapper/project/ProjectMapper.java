package br.senac.bluhelp.mapper.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.model.category.Category;
import br.senac.bluhelp.model.project.Project;

@Service
public class ProjectMapper {

	public ProjectDTO toDTO(Project project) {

		List<Category> categories = project.getCategories();

		List<Long> categoriesProject = new ArrayList<>();

		for (Category category : categories) {
			categoriesProject.add(category.getId());
		}

		return new ProjectDTO(project.getId(), project.getCreator().getId(), project.getTitle(), project.getObjective(),
				project.getDescription(), categoriesProject, project.getAddress().getId(),
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