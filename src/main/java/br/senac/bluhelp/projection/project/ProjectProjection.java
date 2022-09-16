package br.senac.bluhelp.projection.project;

import java.util.List;

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
	
	byte getProgress();

	String getProjectDescription();

	List<CategoryProjection> getCategories();

	byte[] getPhoto();
	
	String getDate();

}
