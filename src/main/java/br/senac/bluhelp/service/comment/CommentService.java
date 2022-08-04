package br.senac.bluhelp.service.comment;

import br.senac.bluhelp.dto.comment.CommentDTO;

public interface CommentService {

	CommentDTO save(CommentDTO comment);

	void edit(Long id, CommentDTO commentDTO);

	void delete(Long id);

}
