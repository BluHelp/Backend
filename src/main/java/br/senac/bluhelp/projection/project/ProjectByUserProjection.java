package br.senac.bluhelp.projection.project;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.user.User;

public interface ProjectByUserProjection {

	Long getId();
	
	User getCreator();
	
	interface CreatorProjection {

		Long getId();

		String getName();

		String getSurname();
	}

	String getTitle();

	Address getAddress();
	
	interface AddressProjection {

		Long getId();

		String getDistrict();
	}

	Progress getProgress();
	
	byte[] getPhoto();

}
