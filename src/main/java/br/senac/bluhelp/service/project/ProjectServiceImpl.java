package br.senac.bluhelp.service.project;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.dto.project.ProjectProjectionDTO;
import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.exception.address.AddressNotFoundException;
import br.senac.bluhelp.exception.project.ProjectNotFoundException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.mapper.project.ProjectMapper;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.category.Category;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectQueryProjection;
import br.senac.bluhelp.repository.address.AddressRepository;
import br.senac.bluhelp.repository.category.CategoryRepository;
import br.senac.bluhelp.repository.project.ProjectRepository;
import br.senac.bluhelp.repository.user.UserRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@PersistenceContext
	private final EntityManager entityManager;
	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;
	private final UserRepository userRepository;
	private final AddressRepository addressRepository;
	private final CategoryRepository categoryRepository;

	public ProjectServiceImpl(EntityManager entityManager, ProjectRepository projectRepository, ProjectMapper projectMapper,
			UserRepository userRepository, AddressRepository addressRepository, CategoryRepository categoryRepository) {
		this.entityManager = entityManager;
		this.projectRepository = projectRepository;
		this.projectMapper = projectMapper;
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
		this.categoryRepository = categoryRepository;
	}

	public ProjectDTO save(ProjectDTO projectDTO) {
		
		User creator = userRepository.findById(projectDTO.creator())
				.orElseThrow(() -> new UserNotFoundException("User " + projectDTO.creator() + " was not found"));

		Address address = addressRepository.findById(projectDTO.address())
				.orElseThrow(() -> new AddressNotFoundException("Address " + projectDTO.address() + " was not found"));

		Progress progress = Progress.values()[projectDTO.progress()];

		Project project = new Project();
		
		project.setDate(LocalDateTime.now());
		project.setAddress(address);
		project.setCreator(creator);
		project.setProgress(progress);
		project.setDescription(projectDTO.description());
		project.setTitle(projectDTO.title());
		project.setObjective(projectDTO.objective());
		project.setPhoto(projectDTO.photo());
		
		creator.addCreatedProject(project);
		
		List<Category> categories = categoryRepository.findAllById(projectDTO.categories());
		
		for(Category category : categories) {
			project.addCategory(category);
			category.addProject(project);
		}
		
		Project projectSaved = projectRepository.save(project);
		
		return projectMapper.toDTO(projectSaved);
	}

	public void update(Long id, ProjectDTO projectDTO) {
		
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));

		Address address = addressRepository.findById(projectDTO.address())
				.orElseThrow(() -> new AddressNotFoundException("Address " + projectDTO.address() + " was not found"));

		Progress progress = Progress.values()[projectDTO.progress()];

		List<Category> categories = categoryRepository.findAllById(projectDTO.categories());
		
		for(Category category : categories) {
			project.addCategory(category);
			category.addProject(project);
		}

		project.setAddress(address);
		project.setObjective(projectDTO.objective());
		project.setProgress(progress);
		project.setDescription(projectDTO.description());
		project.setTitle(projectDTO.title());
		project.setPhoto(projectDTO.photo());

		projectRepository.save(project);

	}

	public void delete(Long id) {
		if (!projectRepository.existsById(id)) {
			throw new ProjectNotFoundException("Project " + id + " was not found");
		}

		projectRepository.deleteById(id);
	}
	
	public ProjectProjectionDTO findProjectWithAverageReviewById(Long id) {
		
		ProjectProjection project = projectRepository.findProjectById(id).orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));
		float average = projectRepository.findAverageReviewById(id);
		
		ProjectProjectionDTO dto = new ProjectProjectionDTO(project.getId(), project.getCreator().getId(), project.getCreator().getName(), project.getCreator().getSurname(), 
				project.getTitle(), project.getObjective(), project.getAddress().getId(), project.getAddress().getDistrict(), project.getDescription(), project.getCategories(), 
				project.getProgress(), project.getPhoto(), project.getDate(), average);
		
		
		return dto;
	}
	
	public List<ProjectQueryProjection> findAll() {
		return projectRepository.findProjects();
	}
	
}