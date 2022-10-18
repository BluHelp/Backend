package br.senac.bluhelp.dto.address;

public record AddressDTO(Long id, String street, short number, String district, String cep, String reference) {
}