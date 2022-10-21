package br.senac.bluhelp.repository.address;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.projection.address.AddressProjection;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	boolean existsById(Long id);

	Optional<AddressProjection> findAddressById(Long id);

	@Query(value = "SELECT a.id AS id, a.street AS street, a.number AS number, a.district AS district, a.cep AS cep FROM Address AS a")
	List<AddressProjection> findAddresses();

}