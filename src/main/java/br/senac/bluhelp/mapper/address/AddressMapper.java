package br.senac.bluhelp.mapper.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.address.AddressDTO;
import br.senac.bluhelp.model.address.Address;

@Service
public class AddressMapper {

	public AddressDTO toDTO(Address address) {

		return new AddressDTO(address.getId(), address.getStreetType(), address.getStreet(), address.getNumber(), address.getDistrict(), address.getCep(), address.getReference());
	}

	public List<AddressDTO> toDTO(List<Address> addresses) {

		final List<AddressDTO> addressesDTO = new ArrayList<>();

		for (Address address : addresses)
			addressesDTO.add(toDTO(address));

		return addressesDTO;

	}

	public Address toEntity(AddressDTO addressDTO) {
		return new Address(addressDTO.id(), addressDTO.streetType(), addressDTO.street(), addressDTO.number(), addressDTO.district(), addressDTO.cep(), addressDTO.reference());
	}
}
