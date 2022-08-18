package br.senac.bluhelp.exception.contact;

public class ContactNotFoundException extends RuntimeException {
	public ContactNotFoundException(String message) {
		super(message);
	}
}
