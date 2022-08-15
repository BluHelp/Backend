package br.senac.bluhelp.service.project;

public interface ProjectService {

	void createProject(Project project);
	void removeProject(Long id);
	void editProject(Projeto projeto);
	
}
