package br.senac.bluhelp.projection.project;

import br.senac.bluhelp.model.user.User;

public interface ProjectWithAddressProjection {

	Long getId ();
	User getCreator();
	String getTitle();
	
	interface ProjectAddressProjection{
		
		Long getId();
		String getRoadType();
		String getStreet();
		Short getNumber();
		String getNeighborhood();
		
	}
}
