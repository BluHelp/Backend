package br.senac.bluhelp.projection.project;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.user.User;

public interface ProjectByUserProjection {

	Long getId();

	User getCreator();

	String getTitle();

	Address getAddress();

	Progress getProgress();

	interface Districtrojection {

		Long getId();

		String getDistrict();
	}

	interface ProjectCreatorProjection {

		Long getId();

		String getName();

		String getSurname();
	}
}
