package br.senac.bluhelp.dto.project;

public record ProjectDTO(Long id, Long creator, String title, String objective, Long address, String description, int progress, int category, byte[] photo) {
}
