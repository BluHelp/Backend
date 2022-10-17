package br.senac.bluhelp.controller.project;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.dto.project.ProjectDescriptionDTO;
import br.senac.bluhelp.dto.project.ProjectImageDTO;
import br.senac.bluhelp.dto.project.ProjectInformationDTO;
import br.senac.bluhelp.dto.project.ProjectPhotoDTO;
import br.senac.bluhelp.dto.project.ProjectProjectionDTO;
import br.senac.bluhelp.dto.project.ProjectQueryDTO;
import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.projection.project.ProjectQueryProjection;
import br.senac.bluhelp.service.project.ProjectService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@PostMapping
	public ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO projectDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(projectDTO));
	}
	
	@PostMapping("/image/{id}")
	public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file, @PathVariable Long id) throws IOException{
		projectService.saveImage(file, id);
		return ResponseEntity.status(HttpStatus.CREATED).body("Foto do projeto cadastrada");
	}
	
	@GetMapping("/getImage/{id}")
    public ResponseEntity<ProjectImageDTO> getImage(@PathVariable Long id) {        
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getImageById(id));
	}
	
	@PutMapping("/{id}/photo")
	public ResponseEntity<String> updateProjectPhoto(@RequestBody ProjectPhotoDTO dto,
			@PathVariable(value = "id") Long id) {
		projectService.updatePhoto(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body("Foto do projeto atualizada");
	}

	@PutMapping("/{id}/information")
	public ResponseEntity<String> updateProjectInformation(@RequestBody ProjectInformationDTO dto,
			@PathVariable(value = "id") Long id) {
		projectService.updateInformation(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body("Informações do projeto atualizadas");
	}

	@PutMapping("/{id}/description")
	public ResponseEntity<String> updateProjectDescription(@RequestBody ProjectDescriptionDTO dto,
			@PathVariable(value = "id") Long id) {
		projectService.updateDescription(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body("Informações do projeto atualizadas");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable(value = "id") Long id) {
		projectService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Projeto deletado");
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectProjectionDTO> getProject(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findProjectWithAverageReviewById(id));
	}

	@GetMapping()
	public ResponseEntity<List<ProjectQueryProjection>> getAllProjects() {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findAll());
	}

	@GetMapping("/progress/{progress}")
	public ResponseEntity<List<ProjectQueryProjection>> getProjectsWithProgress(
			@PathVariable(value = "progress") Progress progress) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByProgress(progress));
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<List<ProjectQueryDTO>> getProjectsWithCategory(@PathVariable(value = "id") Long category) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByCategory(category));
	}

	@GetMapping("/address/district/{district}")
	public ResponseEntity<List<ProjectQueryProjection>> getProjectsWithDistrict(
			@PathVariable(value = "district") String district) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByDistrict(district));
	}

	@GetMapping("/creator/name/{name}/surname/{surname}")
	public ResponseEntity<List<ProjectQueryProjection>> getProjectsWithCreator(
			@PathVariable(value = "name") String name, @PathVariable(value = "surname") String surname) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByCreator(name, surname));
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<List<ProjectQueryProjection>> getProjectsWithTitle(
			@PathVariable(value = "title") String title) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByTitle(title));

	}

	@GetMapping("/default")
	public ResponseEntity<List<ProjectQueryProjection>> getTop4Projects() {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findTop4());
	}

}
