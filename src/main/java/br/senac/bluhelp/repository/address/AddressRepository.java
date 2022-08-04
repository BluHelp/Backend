package br.senac.bluhelp.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.bluhelp.model.address.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
