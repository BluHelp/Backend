package br.senac.bluhelp.service.contact;

public interface ContactService {
	
	void addContact(Contact contact);
	void removeContact(Long id);
	void editContact(Contact contact);
	
	
}
