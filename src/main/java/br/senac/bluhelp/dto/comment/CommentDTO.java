package br.senac.bluhelp.dto.comment;

public record CommentDTO(Long id, String content, Long user, Long project) {
}