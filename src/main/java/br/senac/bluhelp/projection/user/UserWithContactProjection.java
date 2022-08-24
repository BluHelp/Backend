package br.senac.bluhelp.projection.user;

public interface UserWithContactProjection {

	Long getId();
	String getName();
	String getSurname();
	
	interface contactProjection {
		
		Long getId();
		String getEmail();
		String getPhone();	
		
	}
}
