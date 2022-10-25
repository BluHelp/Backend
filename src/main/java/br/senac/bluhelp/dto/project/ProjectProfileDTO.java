package br.senac.bluhelp.dto.project;

import java.time.LocalDateTime;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.projection.category.CategoryProjection;

public record ProjectProfileDTO(Long id, Long creator, String creatorName, String creatorSurname, String title, 
		String objective, Long address, String district, String description, CategoryProjection category, Progress progress, LocalDateTime date, Double averageReview) {
}
