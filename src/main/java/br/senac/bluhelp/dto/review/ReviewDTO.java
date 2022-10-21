package br.senac.bluhelp.dto.review;

import java.time.LocalDateTime;

public record ReviewDTO(Long id, byte rating, Long user, Long project, LocalDateTime date) {
}