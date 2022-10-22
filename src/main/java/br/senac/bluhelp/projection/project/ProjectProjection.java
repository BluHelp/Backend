package br.senac.bluhelp.projection.project;

import java.time.LocalDateTime;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.projection.category.CategoryProjection;

public interface ProjectProjection {

	Long getId();

	UserProjection getCreator();
	
	interface UserProjection {
		
		Long getId();
		
		String getName();
		
		String getSurname();
		
	}

	String getTitle();

	String getObjective();

	AddressDistrictProjection getAddress();
	
	interface AddressDistrictProjection {
		
		Long getId();
		
		String getDistrict();
		
	}
	
	Progress getProgress();

	String getDescription();

	CategoryProjection getCategory();
	
	byte[] getPhoto();
	
	LocalDateTime getDate();

}
