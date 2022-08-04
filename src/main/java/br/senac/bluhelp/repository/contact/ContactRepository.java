package br.senac.bluhelp.repository.contact;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.bluhelp.model.contact.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
