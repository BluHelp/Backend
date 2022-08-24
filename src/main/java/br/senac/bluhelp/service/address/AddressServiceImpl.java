package br.senac.bluhelp.service.address;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.address.AddressDTO;
import br.senac.bluhelp.exception.address.AddressNotFoundException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.mapper.address.AddressMapper;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.projection.address.AddressProjection;
import br.senac.bluhelp.repository.address.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	private final AddressMapper addressMapper;

	public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
		this.addressRepository = addressRepository;
		this.addressMapper = addressMapper;
	}

	public AddressDTO save(AddressDTO addressDTO) {

		Address address = addressMapper.toEntity(addressDTO);
		Address addressSaved = addressRepository.save(address);

		return addressMapper.toDTO(addressSaved);
	}

	public void update(Long id, AddressDTO addressDTO) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFoundException("Project " + id + " was not found"));

		address.setComplement(addressDTO.complement());
		address.setNeighborhood(addressDTO.neighborhood());
		address.setNumber(addressDTO.number());
		address.setRoadType(address.getRoadType());
		address.setStreet(addressDTO.street());

		addressRepository.save(address);

	}

	public void delete(Long id) {
		if (!addressRepository.existsById(id))
			throw new UserNotFoundException("Address " + id + " was not found");

		addressRepository.deleteById(id);

	}

	public AddressProjection findById(Long id) {
		AddressProjection address = addressRepository.findAddressById(id)
				.orElseThrow(() -> new AddressNotFoundException("Address " + id + " was not found"));

		return address;
	}

	public List<AddressProjection> findAll() {
		return addressRepository.findAddresses();
	}

}