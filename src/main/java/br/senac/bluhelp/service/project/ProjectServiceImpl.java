package br.senac.bluhelp.service.project;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.dto.project.ProjectDescriptionDTO;
import br.senac.bluhelp.dto.project.ProjectImageDTO;
import br.senac.bluhelp.dto.project.ProjectInformationDTO;
import br.senac.bluhelp.dto.project.ProjectPhotoDTO;
import br.senac.bluhelp.dto.project.ProjectProfileDTO;
import br.senac.bluhelp.dto.project.ProjectQueryDTO;
import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.exception.project.ProjectNotFoundException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.mapper.project.ProjectMapper;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.category.Category;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectQueryProjection;
import br.senac.bluhelp.repository.category.CategoryRepository;
import br.senac.bluhelp.repository.project.ProjectRepository;
import br.senac.bluhelp.repository.user.UserRepository;
import br.senac.bluhelp.service.address.AddressService;
import br.senac.bluhelp.util.ImageUtil;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;
	private final UserRepository userRepository;
	private final AddressService addressService;
	private final CategoryRepository categoryRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper,
			UserRepository userRepository, AddressService addressService, CategoryRepository categoryRepository) {
		this.projectRepository = projectRepository;
		this.projectMapper = projectMapper;
		this.userRepository = userRepository;
		this.addressService = addressService;
		this.categoryRepository = categoryRepository;
	}

	public ProjectDTO save(ProjectDTO projectDTO) {

		User creator = userRepository.findById(projectDTO.creator())
				.orElseThrow(() -> new UserNotFoundException("User " + projectDTO.creator() + " was not found"));
		
		Address address = new Address(projectDTO.address(), projectDTO.street(), projectDTO.number(), 
				projectDTO.district(), projectDTO.cep(), projectDTO.reference());
		
		addressService.save(address);
		
		Progress progress = Progress.values()[0];

		Project project = new Project();

		project.setDate(LocalDateTime.now());
		project.setAddress(address);
		project.setCreator(creator);
		project.setProgress(progress);
		project.setDescription(projectDTO.description());
		project.setTitle(projectDTO.title());
		project.setObjective(projectDTO.objective());

		creator.addCreatedProject(project);

		List<Category> categories = categoryRepository.findAllById(projectDTO.categories());

		for (Category category : categories) {
			project.addCategory(category);
			category.addProject(project);
		}

		Project projectSaved = projectRepository.save(project);

		return projectMapper.toDTO(projectSaved);
	}
	
	public void saveImage(MultipartFile file, Long id) throws IOException {
		
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));
		
		project.setPhoto(ImageUtil.compressBytes(file.getBytes()));
		
		projectRepository.save(project);
	}
	
	public ProjectImageDTO getImageById(Long id) {
		Project dbImage = projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));
		return new ProjectImageDTO(Base64.getEncoder().encodeToString(ImageUtil.decompressBytes(dbImage.getPhoto())));
	}
	
	public void updatePhoto(Long id, ProjectPhotoDTO dto) {
		
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));
		
		Progress progress = Progress.values()[dto.progress()];
		
		project.setPhoto(dto.photo());
		project.setProgress(progress);
		
		projectRepository.save(project);	
	}
	
	public void updateInformation(Long id, ProjectInformationDTO dto) {
		
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));
		
		List<Category> categories = categoryRepository.findAllById(dto.categories());
		
		for (Category category : project.getCategories()) {
			project.removeCategory(category);
			category.removeProject(project);
		}

		for (Category category : categories) {
			project.addCategory(category);
			category.addProject(project);
		}
		
		project.setTitle(dto.title());
		
		projectRepository.save(project);		
	}
	
	public void updateDescription(Long id, ProjectDescriptionDTO dto) {
		
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));
		
		project.setDescription(dto.description());
		project.setObjective(dto.objective());
		
		projectRepository.save(project);
	}


	public void delete(Long id) {
		if (!projectRepository.existsById(id)) {
			throw new ProjectNotFoundException("Project " + id + " was not found");
		}

		projectRepository.deleteById(id);
	}
	
	public List<ProjectQueryProjection> findTop4() {
		
		Pageable topFour = PageRequest.of(0, 4);
		
		return projectRepository.findTop4ByAverageReview(topFour);
	}
	
	public ProjectProfileDTO findProjectWithAverageReviewById(Long id) {
		
		ProjectProjection project = projectRepository.findProjectById(id).orElseThrow(() -> new ProjectNotFoundException("Project " + id + " was not found"));
		Double average = projectRepository.findAverageReviewById(id);
		
		ProjectProfileDTO dto = new ProjectProfileDTO(project.getId(), project.getCreator().getId(), project.getCreator().getName(), project.getCreator().getSurname(), 
				project.getTitle(), project.getObjective(), project.getAddress().getId(), project.getAddress().getDistrict(), project.getDescription(), project.getCategories(), 
				project.getProgress(), project.getDate(), average);
		
		
		return dto;
	}

	public List<ProjectQueryProjection> findByProgress(Progress progress) {

		return projectRepository.findProjectsByProgress(progress);

	}
	
	public List<ProjectQueryDTO> findByCategory(Long category) {
		
		List<ProjectQueryProjection> projects = projectRepository.findProjectsByCategory(category);
		
		List<ProjectQueryDTO> dtos = new ArrayList<>();
		
		for(ProjectQueryProjection project : projects) {
			
			Double average = projectRepository.findAverageReviewById(project.getId());
			
			ProjectQueryDTO dto = new ProjectQueryDTO(project.getId(), project.getTitle(), project.getProgress(), average);
			
			dtos.add(dto);
			
		}
		
		return dtos;
	}
	
	public List<ProjectQueryProjection> findByDistrict(String district) {
		
		return projectRepository.findProjectsByDistrict(district);
	}
	
	public List<ProjectQueryProjection> findByCreator(String name, String surname) {
		
		return projectRepository.findProjectsByNameAndSurname(name, surname);
	}
	
	public List<ProjectQueryProjection> findAll() {
		
		return projectRepository.findProjects();
	}

	public List<ProjectQueryProjection> findByTitle(String title) {

		return projectRepository.findProjectsByTitle(title);
	}

	public List<ProjectQueryProjection> findByAverage(byte average) {
		return projectRepository.findProjectsByAvg(average);
	}

}