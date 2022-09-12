package br.senac.bluhelp.dto.comment;

public record CommentReplyDTO(Long id, String content, Long user, Long project, Long referencedComment) {

}
