package br.senac.bluhelp.projection.project;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.user.User;

public interface ProjectWithDistrictProjection {

	Long getId();

	User getCreator();

	String getTitle();

	Address getAddress();

	Progress getProgress();
	
	byte[] getPhoto();

	interface DistrictProjection {

		Long getId();

		String getDistrict();
	}

}
