
 package br.senac.bluhelp.service.address;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.mapper.address.AddressMapper;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.repository.address.AddressRepository;


@Service
public class AddressServiceImpl implements AddressService {
	
	private final AddressRepository addressRepository;
	private final AddressMapper addressMapper;
	
	public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
		this.addressRepository = addressRepository;
		this.addressMapper = addressMapper;
	}
	
	
	public void addAddress(Address address) {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteAddress(Long id) {
		// TODO Auto-generated method stub
		if (!addressRepository.existsById(id)) {
			
		}
	}
	
	public void editAddress(Address address) {
		// TODO Auto-generated method stub
		
	}


}