package br.senac.bluhelp.repository.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectWithProgressProjection;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	boolean existsById(Long id);
	
	Optional <ProjectProjection> findProjectById(Long id);
	
	List <ProjectWithProgressProjection> findProjectsByProgress(Progress progress);
	
	
	
	@Query(value= "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress FROM Project as p")
	List<ProjectWithProgressProjection> findProjects();

}