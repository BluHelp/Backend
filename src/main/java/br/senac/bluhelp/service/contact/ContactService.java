package br.senac.bluhelp.service.contact;

import java.util.List;

import br.senac.bluhelp.dto.contact.ContactDTO;
import br.senac.bluhelp.projection.contact.ContactProjection;

public interface ContactService {
	
	ContactDTO save(ContactDTO contactDTO);
	void update(Long id, ContactDTO contactDTO);
	void delete(Long id);
	ContactProjection findById(Long id);
	List<ContactProjection> findAll();
	
	
}
