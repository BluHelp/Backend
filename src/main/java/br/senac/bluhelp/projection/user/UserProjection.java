package br.senac.bluhelp.projection.user;

public interface UserProjection {

	Long getId();
	
	String getName();
	
	String getSurname();
	
	String getCpf();
	
	String getPassword();
	
	byte[] getPhoto();

}