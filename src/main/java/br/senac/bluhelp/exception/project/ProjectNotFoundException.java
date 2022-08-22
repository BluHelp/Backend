package br.senac.bluhelp.exception.project;

public class ProjectNotFoundException extends RuntimeException{
	
	public ProjectNotFoundException(String message) {
		super(message);
	}

}