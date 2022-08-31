package br.senac.bluhelp.controller.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.bluhelp.dto.address.AddressDTO;
import br.senac.bluhelp.projection.address.AddressProjection;
import br.senac.bluhelp.service.address.AddressService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping
	public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(addressDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateAddress(@RequestBody AddressDTO addressDTO, @PathVariable(value = "id") Long id) {
		addressService.update(id, addressDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Endereço atualizado");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAddress(@PathVariable(value = "id") Long id) {
		addressService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado");
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressProjection> getAddress(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(addressService.findById(id));
	}

	@GetMapping()
	public ResponseEntity<List<AddressProjection>> getAllAddresses() {
		return ResponseEntity.status(HttpStatus.OK).body(addressService.findAll());
	}
}
