package br.senac.bluhelp.projection.project;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.user.User;

public interface ProjectWithAddressProjection {

	Long getId();

	User getCreator();

	String getTitle();

	Address getAddress();

	Progress getProgress();

	interface AddressProjection {

		Long getId();

		String getRoadType();

		String getStreet();

		Short getNumber();

		String getDistrict();
		
		String getComplement();
		
		

	}

}
