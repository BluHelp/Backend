package br.senac.bluhelp.dto.project;

import br.senac.bluhelp.enumeration.progress.Progress;

public record ProjectQueryDTO(Long id, String title, Progress progress, Double averageReview) {

}
