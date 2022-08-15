
package br.senac.bluhelp.service.address;

import br.senac.bluhelp.model.address.Address;

public interface AddressService {

	void addAddress(Address address);

	void deleteAddress(Long id);

	void editAddress(Address address);

}