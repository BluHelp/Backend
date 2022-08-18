package br.senac.bluhelp.mapper.contact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.contact.ContactDTO;
import br.senac.bluhelp.dto.project.ProjectDTO;
import br.senac.bluhelp.model.contact.Contact;


@Service
public class ContactMapper {
	
	public ContactDTO toDTO(Contact contact) {
		
		return new ContactDTO(contact.getId(), contact.getEmail(), contact.getPhone(), contact.getUser());
	}
	
	public List<ContactDTO> toDTO(List<Contact> contacts) {
		
		final List<ContactDTO> contactsDTO = new ArrayList<>();
		
		for (Contact contact : contacts)
			contactsDTO.add(toDTO(contact));
		
		return contactsDTO;
	}
	
	public Contact toEntity(ContactDTO contactDTO) {
		return new Contact(contactDTO.id(), contactDTO.email(), contactDTO.phone(), contactDTO.user());
	}
}