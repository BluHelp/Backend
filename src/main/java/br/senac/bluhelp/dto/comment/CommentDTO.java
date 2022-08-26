package br.senac.bluhelp.dto.comment;

import java.time.LocalDateTime;

public record CommentDTO(Long id, String content, Long user, Long project, LocalDateTime date, Long referencedComment) {
}