package br.senac.bluhelp.projection.user;

public interface UserWithContactProjection {

	Long getId();
	String getName();
	
	interface contactProjection {
		
		String getEmail();
		String getPhone();	
		
	}
}
