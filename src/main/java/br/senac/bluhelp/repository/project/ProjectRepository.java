package br.senac.bluhelp.repository.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectQueryProjection;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	boolean existsById(Long id);
  
	List<ProjectQueryProjection> findProjectsByProgress(Progress progress);

	Optional<ProjectProjection> findProjectById(Long id);

	List<ProjectQueryProjection> findProjectsByProgress(Progress progress);
	
	List<ProjectQueryProjection> findProjectsByTitle(String title);

	@Query(value = "SELECT AVG(r.rating) AS averageReview FROM Project as p INNER JOIN p.reviews r WHERE r.project.id = ?1")
	float findAverageReviewById(Long id);

	@Query(value = "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress, AVG(r.rating) AS averageReview FROM Project as p INNER JOIN p.reviews r GROUP BY p.id")
	List<ProjectQueryProjection> findProjects();

}