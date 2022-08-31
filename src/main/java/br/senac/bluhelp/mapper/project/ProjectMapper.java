package br.senac.bluhelp.mapper.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.enumeration.progress.*;
import br.senac.bluhelp.exception.address.AddressNotFoundException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.enumeration.category.*;
import br.senac.bluhelp.repository.user.UserRepository;
import br.senac.bluhelp.repository.address.AddressRepository;

@Service
public class ProjectMapper {

	private final UserRepository userRepository;
	private final AddressRepository addressRepository;

	public ProjectMapper(UserRepository userRepository, AddressRepository addressRepository) {
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
	}

	public ProjectDTO toDTO(Project project) {

		return new ProjectDTO(project.getId(), project.getCreator().getId(), project.getTitle(), project.getObjective(),
				project.getAddress().getId(), project.getDescription(), project.getProgress().ordinal(),
				project.getCategory().ordinal(), project.getPhoto());
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

		Category category = Category.values()[projectDTO.category()];

		return new Project(projectDTO.id(), creator, projectDTO.title(), projectDTO.objective(), address,
				projectDTO.description(), progress, category, projectDTO.photo());
	}
}