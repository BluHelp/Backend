package br.senac.bluhelp.repository.contact;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.model.contact.Contact;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.projection.contact.ContactProjection;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	boolean existsById(Long id);
	
	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);
	
	boolean existsByUser(User user);
	
	Optional <ContactProjection> findContactById(Long id);
	
	Optional<ContactProjection> findByEmailContainsIgnoreCase(String email);
	
	@Query(value= "SELECT ct.email AS email, ct.id AS id, ct.phone AS phone, ct.user AS user FROM Contact as ct")
	List<ContactProjection> findContacts();
}