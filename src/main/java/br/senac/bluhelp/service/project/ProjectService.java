package br.senac.bluhelp.service.project;

import java.util.List;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.projection.project.ProjectByUserProjection;
import br.senac.bluhelp.projection.project.ProjectProjection;
import br.senac.bluhelp.projection.project.ProjectWithAddressProjection;
import br.senac.bluhelp.projection.project.ProjectWithDistrictProjection;
import br.senac.bluhelp.projection.project.ProjectWithReviewsProjection;

public interface ProjectService {

	ProjectDTO save(ProjectDTO projectDTO);

	void update(Long id, ProjectDTO projectDTO);

	void delete(Long id);

	ProjectProjection findById(Long id);

	ProjectByUserProjection findByIdWithUser(Long id);
	
	ProjectWithDistrictProjection findByIdWithDistrict(Long id);

	ProjectWithAddressProjection findByIdWithAddress(Long id);

	ProjectWithReviewsProjection findByIdWithReview(Long id);

	List<ProjectProjection> findAll();

}
