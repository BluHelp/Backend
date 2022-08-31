package br.senac.bluhelp.mapper.contact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.contact.ContactDTO;
import br.senac.bluhelp.exception.contact.ContactNotFoundException;
import br.senac.bluhelp.model.contact.Contact;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.repository.user.UserRepository;

@Service
public class ContactMapper {

	private final UserRepository userRepository;

	public ContactMapper(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ContactDTO toDTO(Contact contact) {

		return new ContactDTO(contact.getId(), contact.getEmail(), contact.getPhone(), contact.getUser().getId());
	}

	public List<ContactDTO> toDTO(List<Contact> contacts) {

		final List<ContactDTO> contactsDTO = new ArrayList<>();

		for (Contact contact : contacts)
			contactsDTO.add(toDTO(contact));

		return contactsDTO;
	}

	public Contact toEntity(ContactDTO contactDTO) {

		User user = userRepository.findById(contactDTO.user())
				.orElseThrow(() -> new ContactNotFoundException("Contact " + contactDTO.user() + " was not found"));

		return new Contact(contactDTO.id(), contactDTO.email(), contactDTO.phone(), user);
	}
}