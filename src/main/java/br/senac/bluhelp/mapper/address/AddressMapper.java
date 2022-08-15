package br.senac.bluhelp.mapper.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.address.AddressDTO;
import br.senac.bluhelp.model.address.Address;

@Service
public class AddressMapper {

	public AddressDTO toDTO(Address address) {

		return new AddressDTO(address.getId(), address.getRoadType(), address.getStreet(), address.getNumber(),
				address.getNeighborhood(), address.getComplement());
	}

	public List<AddressDTO> toDTO(List<Address> neighborhood) {

		final List<AddressDTO> neighborhoodDTO = new ArrayList<>();

		for (Address address : neighborhood)
			neighborhoodDTO.add(toDTO(address));

		return neighborhoodDTO;

	}

	public Address toEntity(AddressDTO dto) {
		return new Address(dto.id(), dto.roadType(), dto.street(), dto.number(), dto.number(), dto.neighborhood(),
				dto.complement());
	}
}
