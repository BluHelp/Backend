package br.senac.bluhelp.projection.address;

public interface AddressProjection {

	Long getId();
	String getRoadType();
	String getStreet();
	short getNumber();
	String getNeighborhood();
	String getComplement();
	
}
