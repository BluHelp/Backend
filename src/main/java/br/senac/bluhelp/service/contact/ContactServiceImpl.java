package br.senac.bluhelp.service.contact;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.contact.ContactDTO;
import br.senac.bluhelp.exception.contact.ContactEmailRegisteredException;
import br.senac.bluhelp.exception.contact.ContactNotFoundException;
import br.senac.bluhelp.exception.contact.ContactPhoneRegisteredException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.mapper.contact.ContactMapper;
import br.senac.bluhelp.model.contact.Contact;
import br.senac.bluhelp.projection.contact.ContactProjection;
import br.senac.bluhelp.repository.contact.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactRepository;
	private final ContactMapper contactMapper;

	public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
		this.contactRepository = contactRepository;
		this.contactMapper = contactMapper;
	}

	public ContactDTO save(Contact contact) {
		if (contactRepository.existsByEmail(contact.getEmail()))
			throw new ContactEmailRegisteredException("Email " + contact.getEmail() + " is already registered");

		if (contactRepository.existsByPhone(contact.getPhone()))
			throw new ContactPhoneRegisteredException("Phone " + contact.getPhone() + " is already registered");
		
		Contact contactSaved = contactRepository.save(contact);

		return contactMapper.toDTO(contactSaved);
	}

	public void update(Long id, ContactDTO contactDTO) {
		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Contact " + id + " was not found"));

		if (!contactDTO.email().equals(contact.getEmail())) {
			if (contactRepository.existsByEmail(contactDTO.email())) {
				throw new ContactEmailRegisteredException("Email " + contactDTO.email() + " is already registered");
			}
		}

		if (!contactDTO.phone().equals(contact.getPhone())) {
			if (contactRepository.existsByPhone(contactDTO.phone())) {
				throw new ContactPhoneRegisteredException("Phone " + contactDTO.phone() + " is already registered");
			}
		}

		contact.setEmail(contactDTO.email());
		contact.setPhone(contactDTO.phone());

		contactRepository.save(contact);

	}

	public void delete(Long id) {
		if (!contactRepository.existsById(id))
			throw new UserNotFoundException("Contact " + id + " was not found");

		contactRepository.deleteById(id);
	}

	public ContactProjection findById(Long id) {
		ContactProjection contact = contactRepository.findContactById(id)
				.orElseThrow(() -> new ContactNotFoundException("Contact " + id + " was not found"));

		return contact;
	}

	public List<ContactProjection> findAll() {
		return contactRepository.findContacts();
	}

}
