package br.senac.bluhelp.mapper.contact;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.contact.ContactDTO;
import br.senac.bluhelp.model.contact.Contact;

@Service
public class ContactMapper {

	public ContactDTO toDTO(Contact contact) {

		return new ContactDTO(contact.getId(), contact.getEmail(), contact.getPhone(), contact.getUser().getId());
	}

}