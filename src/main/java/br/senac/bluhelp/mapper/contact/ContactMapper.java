package br.senac.bluhelp.mapper.contact;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.model.contact.Contact;


@Service
public class ContactMapper {
	
	public ContactDTO toDTO(Contact contact) {
		
		return new ContactDTO(contact.getID(), contact.getEmail(), contact.getPhone(), contact.getUser());
	}
	
	public List<ContactDTO> toDTO(List<Contact> user) {
		
		final List<ContactDTO> userDTO = new ArrayList<>();
		
		for (Contact contact : user)
			userDTO.add(toDTO(project));
		
		return userDTO;
	}
	
	public Contact toEntity(ProjectDTO dto) {
		return new Project(dto.id, dto.email, dto.phone, dto.user);
	}
}