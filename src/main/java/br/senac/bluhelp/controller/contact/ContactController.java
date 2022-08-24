package br.senac.bluhelp.controller.contact;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.bluhelp.dto.contact.ContactDTO;
import br.senac.bluhelp.projection.contact.ContactProjection;
import br.senac.bluhelp.service.contact.ContactService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/contact")
public class ContactController {

private final ContactService contactService;
	
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@PostMapping
	public ResponseEntity<ContactDTO> addContact(@RequestBody ContactDTO contactDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contactDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateContact(@RequestBody ContactDTO contactDTO, @PathVariable(value = "id") Long id) {
		contactService.update(id, contactDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Contato atualizado");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContact(@PathVariable(value = "id") Long id) {
		contactService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Contato deletado");
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContactProjection> getUser(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(contactService.findById(id));
	}

	@GetMapping()
	public ResponseEntity<List<ContactProjection>> getAllContacts() {
		return ResponseEntity.status(HttpStatus.OK).body(contactService.findAll());
	}
	
}
