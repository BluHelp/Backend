package br.senac.bluhelp.dto.project;

public record ProjectDTO(Long id, Long creator, String title, String objective, String description,
		Long category, Long address, String street, short number, String district, 
		String cep, String reference) {
}
