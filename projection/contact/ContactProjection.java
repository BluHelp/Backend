package br.senac.bluhelp.projection.contact;

import br.senac.bluhelp.model.user.User;

public interface ContactProjection {
	
	Long getId();
	String getEmail();
	String getPhone();
	User getUser();

}