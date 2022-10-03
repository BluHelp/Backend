package br.senac.bluhelp.controller.project;

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
import org.springframework.web.bind.annotation.RestController;

import br.senac.bluhelp.dto.project.ProjectDTO;
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

	@PutMapping("/{id}")
	public ResponseEntity<String> updateProject(@RequestBody ProjectDTO projectDTO,
			@PathVariable(value = "id") Long id) {
		projectService.update(id, projectDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Projeto atualizado");
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
	public ResponseEntity<List<ProjectQueryProjection>> getProjectWithProgress(
			@PathVariable(value = "progress") Progress progress) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByProgress(progress));
	}
	

	@GetMapping("/category/{id}")
	public ResponseEntity<List<ProjectQueryDTO>> getProjectWithCategory(
			@PathVariable(value = "id") Long category) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByCategory(category));
	}

	@GetMapping("/address/district/{district}")
	public ResponseEntity<List<ProjectQueryProjection>> getProjectWithDistrict(
			@PathVariable(value = "district") String district) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByDistrict(district));
	}
	
	@GetMapping("/creator/name/{name}/surname/{surname}")
	public ResponseEntity<List<ProjectQueryProjection>> getProjectWithCreator(
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
