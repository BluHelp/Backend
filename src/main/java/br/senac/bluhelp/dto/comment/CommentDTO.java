package br.senac.bluhelp.dto.comment;

import java.time.LocalDateTime;

public record CommentDTO(Long id, String contents, Long user, Long project, LocalDateTime date, Long referenceComment) {}