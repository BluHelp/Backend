package br.senac.bluhelp.dto.project;

import java.util.List;

public record ProjectDTO(Long id, Long creator, String title, String objective, String description,
		List<Long> categories, Long address, String street, short number, String district, 
		String cep, String reference) {
}
