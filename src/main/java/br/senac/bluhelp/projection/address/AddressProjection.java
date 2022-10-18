package br.senac.bluhelp.projection.address;

public interface AddressProjection {

	Long getId();
	
	String getStreet();
	
	short getNumber();
	
	String getDistrict();
	
	String getCep();
	
	String getReference();

}
