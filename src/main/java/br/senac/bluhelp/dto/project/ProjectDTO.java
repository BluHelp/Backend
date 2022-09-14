package br.senac.bluhelp.dto.project;

import java.util.List;

public record ProjectDTO(Long id, Long creator, String title, String objective, Long address, String description,
		List<Long> categories, int progress, byte[] photo) {
}
