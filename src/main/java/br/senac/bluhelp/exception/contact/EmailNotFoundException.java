package br.senac.bluhelp.exception.contact;

public class EmailNotFoundException extends RuntimeException {
	public  EmailNotFoundException(String message) {
		super(message);
	}
}
