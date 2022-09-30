package br.senac.bluhelp.dto.project;

import java.time.LocalDateTime;
import java.util.List;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.projection.category.CategoryProjection;

public record ProjectProjectionDTO(Long id, Long creator, String creatorName, String creatorSurname, String title, 
		String objective, Long address, String district, String description, List<CategoryProjection> categories, Progress progress, byte[] photo, LocalDateTime date, Double averageReview) {
}
