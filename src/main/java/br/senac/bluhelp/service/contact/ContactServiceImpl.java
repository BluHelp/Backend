package br.senac.bluhelp.service.contact;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.contact.ContactDTO;
import br.senac.bluhelp.exception.contact.ContactEmailRegisteredException;
import br.senac.bluhelp.exception.contact.ContactPhoneRegisteredException;
import br.senac.bluhelp.exception.user.UserCpfRegisteredException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.mapper.contact.ContactMapper;
import br.senac.bluhelp.model.contact.Contact;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.projection.contact.ContactProjection;
import br.senac.bluhelp.projection.user.UserProjection;
import br.senac.bluhelp.repository.contact.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactRepository;
	private final ContactMapper contactMapper;

	public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
		this.contactRepository = contactRepository;
		this.contactMapper = contactMapper;

	}

	public ContactDTO save(ContactDTO contactDTO) {
		if (contactRepository.existsByEmail(contactDTO.email()))
			throw new ContactEmailRegisteredException("Email " + contactDTO.email() + " is already registered");

		if (contactRepository.existsByPhone(contactDTO.phone()))
			throw new ContactPhoneRegisteredException("Phone " + contactDTO.phone() + " is already registered");

		Contact contact = contactMapper.toEntity(contactDTO);
		Contact contactSaved = contactRepository.save(contact);

		return contactMapper.toDTO(contactSaved);
	}

	public void update(Long id, ContactDTO contactDTO) {
		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Contact " + id + " was not found"));

		if (contactRepository.existsByEmail(contactDTO.email()))
			throw new ContactEmailRegisteredException("Email " + contactDTO.email() + " is already registered");

		if (contactRepository.existsByPhone(contactDTO.phone()))
			throw new ContactPhoneRegisteredException("Phone " + contactDTO.phone() + " is already registered");

		contact.setEmail(contactDTO.email());
		contact.setPhone(contactDTO.phone());
		contact.setUser(contactDTO.user());

		contactRepository.save(contact);

	}

	public void delete(Long id) {
		if (!contactRepository.existsById(id))
			throw new UserNotFoundException("Contact " + id + " was not found");

		contactRepository.deleteById(id);
	}

	public ContactProjection findById(Long id) {
		ContactProjection contact = contactRepository.findContactById(id)
				.orElseThrow(() -> new UserNotFoundException("Contact " + id + " was not found"));

		return contact;
	}

	public List<ContactProjection> findAll() {
		return contactRepository.findContacts();
	}

}
