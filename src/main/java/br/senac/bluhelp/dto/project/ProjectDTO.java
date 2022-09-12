package br.senac.bluhelp.dto.project;

import java.time.LocalDateTime;
import java.util.List;

public record ProjectDTO(Long id, Long creator, String title, String objective, Long address, String description,
		LocalDateTime date, List<Long> categories, int progress, byte[] photo) {
}
