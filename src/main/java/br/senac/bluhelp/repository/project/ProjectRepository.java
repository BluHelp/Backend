package br.senac.bluhelp.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.bluhelp.model.project.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
