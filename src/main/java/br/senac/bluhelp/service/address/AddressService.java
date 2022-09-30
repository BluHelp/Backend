package br.senac.bluhelp.service.address;

import br.senac.bluhelp.projection.address.AddressProjection;
import br.senac.bluhelp.projection.address.AddressWithProjectsProjection;


import java.util.List;

import br.senac.bluhelp.dto.address.AddressDTO;
import br.senac.bluhelp.projection.address.AddressProjection;

public interface AddressService {

	AddressDTO save(AddressDTO addressDTO);

	void update(Long id, AddressDTO addressDTO);

	void delete(Long id);

	AddressProjection findById(Long id);

	AddressWithProjectsProjection findByDistrict(String district);

	List<AddressProjection> findAll();

}