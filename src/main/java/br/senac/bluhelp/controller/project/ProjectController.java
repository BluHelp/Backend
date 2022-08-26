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
import br.senac.bluhelp.projection.project.ProjectByUserProjection;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectWithAddressProjection;
import br.senac.bluhelp.projection.project.ProjectWithDistrictProjection;
import br.senac.bluhelp.projection.project.ProjectWithReviewsProjection;
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
	public ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO projectDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(projectDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateProject(@RequestBody ProjectDTO projectDTO, @PathVariable(value = "id") Long id) {
		projectService.update(id, projectDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Projeto atualizado");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable(value = "id") Long id) {
		projectService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Projeto deletado");
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectProjection> getUser(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findById(id));
	}
	
	@GetMapping("/{id}/creator")
	public ResponseEntity<ProjectByUserProjection> getProjectByUser(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByIdWithUser(id));
	}
	
	@GetMapping("/{id}/address/district")
	public ResponseEntity<ProjectWithDistrictProjection> getProjectWithDistrict(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByIdWithDistrict(id));
	}

	@GetMapping("/{id}/address")
	public ResponseEntity<ProjectWithAddressProjection> getProjectWithAddress(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByIdWithAddress(id));
	}
	
	@GetMapping("/{id}/reviews")
	public ResponseEntity<ProjectWithReviewsProjection> getProjecctWithReviews(@PathVariable(value = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findByIdWithReview(id));
	}

	@GetMapping()
	public ResponseEntity<List<ProjectProjection>> getAllProjects() {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findAll());
	}
	
}

