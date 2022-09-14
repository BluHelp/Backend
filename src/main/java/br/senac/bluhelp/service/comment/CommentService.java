package br.senac.bluhelp.service.comment;

import java.util.List;

import br.senac.bluhelp.dto.comment.CommentDTO;
import br.senac.bluhelp.dto.comment.CommentReplyDTO;
import br.senac.bluhelp.projection.comment.CommentProjection;

public interface CommentService {

	CommentDTO save(CommentDTO commentDTO);
	
	CommentReplyDTO save(CommentReplyDTO commentReplyDTO);

	void update(Long id, CommentDTO commentDTO);
	
	void update(Long id, CommentReplyDTO commentReplyDTO);

	void delete(Long id);
	
	CommentProjection findById(Long id);
	
	List<CommentProjection> findAll();
}
