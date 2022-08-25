package br.senac.bluhelp.service.address;


import br.senac.bluhelp.projection.address.AddressProjection;

import java.util.List;

import br.senac.bluhelp.dto.address.AddressDTO;

public interface AddressService {

	AddressDTO save(AddressDTO addressDTO);
	
	void update(Long id, AddressDTO addressDTO);

	void delete(Long id);
	
	AddressProjection findById(Long id);
	
	List<AddressProjection> findAll();
	
}