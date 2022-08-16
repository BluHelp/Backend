package br.senac.bluhelp.repository.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectWithAddressProjection;
import br.senac.bluhelp.projection.project.ProjectWithReviewsProjection;
import br.senac.bluhelp.repository.project.ProjectRepository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	boolean existsById(Long id);
	
	boolean existsByPhoto(byte[] photo);
	
	Optional <ProjectProjection> findProjectById(Long id);
	
	Optional <ProjectWithAddressProjection> findProjectWithAddressById(Long id);
	
	Optional <ProjectWithReviewsProjection> findProjectWithReviewsById(Long id);
	
	@Query(value= "SELECT p.title AS title, p.id AS id, p.creator AS creator, p.address AS address, p.category AS category, p.reviews AS reviews, p.progress AS progress FROM Project")
	List<ProjectProjection> findProjects();
}
