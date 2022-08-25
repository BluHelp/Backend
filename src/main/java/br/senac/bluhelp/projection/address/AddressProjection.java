package br.senac.bluhelp.projection.address;

public interface AddressProjection {

	Long getId();
	String getStreettype();
	String getStreet();
	short getNumber();
	String getDistrict();
	String getCep();
	String getReference();

}
