package br.senac.bluhelp.exception.category;

public class CategoryNotFoundException extends RuntimeException {
	
	public CategoryNotFoundException(String message) {
		super(message);
	}
}
