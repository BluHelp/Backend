package br.senac.bluhelp.dto.project;

import br.senac.bluhelp.enumeration.progress.Progress;

public record ProjectQueryDTO(Long id, String title, byte[] photo, Progress progress, Double averageReview) {

}
