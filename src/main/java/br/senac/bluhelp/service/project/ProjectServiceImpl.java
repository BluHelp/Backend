package br.senac.bluhelp.service.project;

import br.senac.bluhelp.repository.project.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;

	public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
		this.projectRepository = projectRepository;
		this.projectMapper = projectMapper;
	}

	@Override
	public void createProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProject(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editProject(Projeto projeto) {
		// TODO Auto-generated method stub
		
	}
}
