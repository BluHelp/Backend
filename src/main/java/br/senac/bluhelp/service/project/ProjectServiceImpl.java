package br.senac.bluhelp.service.project;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.enumeration.category.Category;
import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.exception.address.AddressNotFoundException;
import br.senac.bluhelp.exception.project.ProjectNotFoundException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.projection.project.ProjectByUserProjection;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectWithAddressProjection;
import br.senac.bluhelp.projection.project.ProjectWithDistrictProjection;
import br.senac.bluhelp.projection.project.ProjectWithReviewsProjection;
import br.senac.bluhelp.repository.address.AddressRepository;
import br.senac.bluhelp.repository.project.ProjectRepository;
import br.senac.bluhelp.repository.user.UserRepository;
import br.senac.bluhelp.mapper.project.ProjectMapper;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;
	private final UserRepository userRepository;
	private final AddressRepository addressRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, UserRepository userRepository, AddressRepository addressRepository) {
		this.projectRepository = projectRepository;
		this.projectMapper = projectMapper;
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
	}

	public ProjectDTO save(ProjectDTO projectDTO) {

		Project project = projectMapper.toEntity(projectDTO);
		Project projectSaved = projectRepository.save(project);

		return projectMapper.toDTO(projectSaved);

	}

	public void update(Long id, ProjectDTO projectDTO) {
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));
		
		User creator = userRepository.findById(projectDTO.creator()).orElseThrow(() -> new UserNotFoundException("User " + projectDTO.creator() + " was not found"));

		Address address = addressRepository.findById(projectDTO.address()).orElseThrow(() -> new AddressNotFoundException("Address " + projectDTO.address() + " was not found"));
		
		Progress progress = Progress.values()[projectDTO.progress()];
		
		Category category = Category.values()[projectDTO.category()];
		
		project.setAddress(address);
		project.setCreator(creator);
		project.setObjective(projectDTO.objective());
		project.setProgress(progress);
		project.setDescription(projectDTO.description());
		project.setTitle(projectDTO.title());
		project.setCategory(category);
		project.setPhoto(projectDTO.photo());

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

	public ProjectWithAddressProjection findByIdWithAddress(Long id) {
		ProjectWithAddressProjection project = projectRepository.findProjectWithAddressById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));

		return project;
	}

	public ProjectWithReviewsProjection findByIdWithReview(Long id) {
		ProjectWithReviewsProjection project = projectRepository.findProjectWithReviewsById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));

		return project;
	}

	public ProjectByUserProjection findByIdWithUser(Long id) {
		ProjectByUserProjection project = projectRepository.findProjectWithUserById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project" + id + " was not found"));

		return project;
	}

	public ProjectWithDistrictProjection findByIdWithDistrict(Long id) {
		ProjectWithDistrictProjection project = projectRepository.findProjectWithDistrictById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project" + id + " was not found"));

		return project;
	}

	public List<ProjectProjection> findAll() {
		return projectRepository.findProjects();
	}

}