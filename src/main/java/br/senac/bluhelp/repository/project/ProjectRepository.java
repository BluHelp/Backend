package br.senac.bluhelp.repository.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectWithProgressProjection;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	boolean existsById(Long id);
	
	Optional <ProjectProjection> findProjectById(Long id);
	
	Optional <ProjectWithProgressProjection> findProjectWithProgressById(Long id);
	
	@Query(value= "SELECT p.title AS title, p.id AS id, p.creator AS creator, p.address AS address, p.categories AS categories, p.reviews AS reviews, p.progress AS progress FROM Project as p")
	List<ProjectWithProgressProjection> findProjects();
	
	//@Query(value = "SELECT avg(r.rating) as average FROM Review as r WHERE r.project = ?1")
	//byte findProjectWithAverageReviewById(Long id);

}