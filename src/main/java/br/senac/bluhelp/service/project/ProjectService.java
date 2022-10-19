package br.senac.bluhelp.service.project;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.dto.project.ProjectDescriptionDTO;
import br.senac.bluhelp.dto.project.ProjectImageDTO;
import br.senac.bluhelp.dto.project.ProjectInformationDTO;
import br.senac.bluhelp.dto.project.ProjectPhotoDTO;
import br.senac.bluhelp.dto.project.ProjectProfileDTO;
import br.senac.bluhelp.dto.project.ProjectQueryDTO;
import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.projection.project.ProjectQueryProjection;


public interface ProjectService {

	ProjectDTO save(ProjectDTO projectDTO);
	
	void saveImage(MultipartFile file, Long id) throws IOException;
	
	ProjectImageDTO getImageById(Long id);

	void updatePhoto(Long id, ProjectPhotoDTO dto);
	
	void updateInformation(Long id, ProjectInformationDTO dto);
	
	void updateDescription(Long id, ProjectDescriptionDTO dto);

	void delete(Long id);
	
	List<ProjectQueryProjection> findTop4();

	ProjectProfileDTO findProjectWithAverageReviewById(Long id);

	List<ProjectQueryProjection> findByProgress(Progress progress);
	
	List<ProjectQueryDTO> findByCategory(Long category);
	
	List<ProjectQueryProjection> findByDistrict(String district);
	
	List<ProjectQueryProjection> findByCreator(String name, String surname);

	List<ProjectQueryProjection> findByTitle(String title);
	
	List<ProjectQueryProjection> findByAverage(byte average);

	List<ProjectQueryProjection> findAll();

}
