package br.senac.bluhelp.repository.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectQueryProjection;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	boolean existsById(Long id);
	
	@Query(value = "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress, AVG(r.rating) AS averageReview FROM Project as p INNER JOIN p.reviews r GROUP BY p.id ORDER BY AVG(r.rating) DESC")
	List<ProjectQueryProjection> findTop4ByAverageReview(Pageable pageable);
	
	@Query(value = "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress, AVG(r.rating) AS averageReview FROM Project as p INNER JOIN p.reviews r WHERE p.progress = :progress GROUP BY p.id")
	List<ProjectQueryProjection> findProjectsByProgress(@Param("progress") Progress progress);
	
	@Query(value = "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress FROM Project as p INNER JOIN p.categories ca WHERE ca.id = :id GROUP BY p.id")
	List<ProjectQueryProjection> findProjectsByCategory(@Param("id") Long category); 
	
	@Query(value = "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress, AVG(r.rating) AS averageReview FROM Project as p INNER JOIN p.reviews r WHERE p.address.district = :district GROUP BY p.id")
	List<ProjectQueryProjection> findProjectsByDistrict(@Param("district") String district);
	
	@Query(value = "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress, AVG(r.rating) AS averageReview FROM Project as p INNER JOIN p.reviews r WHERE p.creator.name = :name AND p.creator.surname = :surname GROUP BY p.id")
	List<ProjectQueryProjection> findProjectsByNameAndSurname(String name, String surname);

	Optional<ProjectProjection> findProjectById(Long id);
	
	@Query(value = "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress, AVG(r.rating) AS averageReview FROM Project as p INNER JOIN p.reviews r WHERE p.title = :title GROUP BY p.id")
	List<ProjectQueryProjection> findProjectsByTitle(String title);

	@Query(value = "SELECT AVG(r.rating) AS averageReview FROM Project as p INNER JOIN p.reviews r WHERE r.project.id = ?1")
	Double findAverageReviewById(Long id);
	
	@Query(value = "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress, AVG(r.rating) AS averageReview FROM Project as p INNER JOIN p.reviews r WHERE p.creator.id = :id GROUP BY p.id")
	List<ProjectQueryProjection> findCreatedProjectsByUser(@Param("id") Long user);
	
	@Query(value = "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress FROM Project as p INNER JOIN p.contributors co WHERE co.id = :id GROUP BY p.id")
	List<ProjectQueryProjection> findContributedProjectsByUser(@Param("id") Long user);

	@Query(value = "SELECT p.title AS title, p.id AS id, p.photo AS photo, p.progress AS progress, AVG(r.rating) AS averageReview FROM Project as p INNER JOIN p.reviews r GROUP BY p.id")
	List<ProjectQueryProjection> findProjects();

}