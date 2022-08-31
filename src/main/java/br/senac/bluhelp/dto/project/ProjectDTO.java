package br.senac.bluhelp.dto.project;

import java.time.LocalDateTime;

public record ProjectDTO(Long id, Long creator, String title, String objective, Long address, String description,
		LocalDateTime date, int progress, int category, byte[] photo) {
}
