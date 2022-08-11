package br.senac.bluhelp.dto.review;

import java.time.LocalDateTime;

public record ReviewDTO(Long id, byte grade, Long user, Long project, LocalDateTime date) {}