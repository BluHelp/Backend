package br.senac.bluhelp.exception.address;

public class AddressNotFoundException extends RuntimeException {
	public AddressNotFoundException(String message) {
		super(message);
	}
}