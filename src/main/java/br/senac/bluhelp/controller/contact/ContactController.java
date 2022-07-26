package br.senac.bluhelp.controller.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.bluhelp.dto.contact.ContactDTO;
import br.senac.bluhelp.model.contact.Contact;
import br.senac.bluhelp.service.contact.ContactService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private final ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@PostMapping
	public ResponseEntity<ContactDTO> addContact(@RequestBody Contact contact) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contact));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateContact(@RequestBody ContactDTO contactDTO,
			@PathVariable(value = "id") Long id) {
		contactService.update(id, contactDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Contato atualizado");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContact(@PathVariable(value = "id") Long id) {
		contactService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Contato deletado");
	}

}
