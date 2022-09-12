package br.senac.bluhelp.mapper.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.category.Category;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.enumeration.progress.*;
import br.senac.bluhelp.exception.address.AddressNotFoundException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.repository.user.UserRepository;
import br.senac.bluhelp.repository.address.AddressRepository;
import br.senac.bluhelp.repository.category.CategoryRepository;

@Service
public class ProjectMapper {

	private final UserRepository userRepository;
	private final AddressRepository addressRepository;
	private final CategoryRepository categoryRepository;

	public ProjectMapper(UserRepository userRepository, AddressRepository addressRepository,
			CategoryRepository categoryRepository) {
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
		this.categoryRepository = categoryRepository;
	}

	public ProjectDTO toDTO(Project project) {

		List<Category> categories = project.getCategories();

		List<Long> categoriesProject = new ArrayList<>();

		for (Category category : categories) {
			categoriesProject.add(category.getId());
		}

		return new ProjectDTO(project.getId(), project.getCreator().getId(), project.getTitle(), project.getObjective(),
				project.getAddress().getId(), project.getDescription(), project.getDate(), categoriesProject,
				project.getProgress().ordinal(), project.getPhoto());
	}

	public List<ProjectDTO> toDTO(List<Project> contributors) {

		final List<ProjectDTO> contributorsDTO = new ArrayList<>();

		for (Project project : contributors)
			contributorsDTO.add(toDTO(project));

		return contributorsDTO;

	}

	public Project toEntity(ProjectDTO projectDTO) {

		User creator = userRepository.findById(projectDTO.creator())
				.orElseThrow(() -> new UserNotFoundException("User " + projectDTO.creator() + " was not found"));

		Address address = addressRepository.findById(projectDTO.address())
				.orElseThrow(() -> new AddressNotFoundException("Address " + projectDTO.address() + " was not found"));

		Progress progress = Progress.values()[projectDTO.progress()];

		List<Category> categories = categoryRepository.findAllById(projectDTO.categories());

		return new Project(projectDTO.id(), creator, projectDTO.title(), projectDTO.objective(), address,
				projectDTO.description(), projectDTO.date(), categories, progress, projectDTO.photo());
	}
}