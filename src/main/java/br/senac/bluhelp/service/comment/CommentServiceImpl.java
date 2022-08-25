package br.senac.bluhelp.service.comment;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.comment.CommentDTO;
import br.senac.bluhelp.exception.comment.CommentNotFoundException;
import br.senac.bluhelp.mapper.comment.CommentMapper;
import br.senac.bluhelp.model.comment.Comment;
import br.senac.bluhelp.projection.comment.CommentProjection;
import br.senac.bluhelp.repository.comment.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;
	
	public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
		this.commentRepository = commentRepository;
		this.commentMapper = commentMapper;
	}

	public CommentDTO save(CommentDTO commentDTO) {
		
		Comment comment = commentMapper.toEntity(commentDTO);
		Comment commentSaved = commentRepository.save(comment);
		
		return commentMapper.toDTO(commentSaved);
	}

	public void update(Long id, CommentDTO commentDTO) {
		
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment " + id + " was not found"));
		
		comment.setContent(commentDTO.content());
		comment.setUser(commentDTO.user());
		comment.setProject(commentDTO.project());
		comment.setDate(commentDTO.date());
		comment.setReferencedComment(commentDTO.referencedComment());
		
		commentRepository.save(comment);
	}

	public void delete(Long id) {
		
		if(!commentRepository.existsById(id)) {
			throw new CommentNotFoundException("Comment " + id + " was not found");
		}
		
		commentRepository.deleteById(id);
	}

	public CommentProjection findById(Long id) {
		
		CommentProjection comment = commentRepository.findCommentById(id).orElseThrow(() -> new CommentNotFoundException("Comment " + id + " was not found"));
		
		return comment;
	}

	public List<CommentProjection> findAll() {
		return commentRepository.findComments();
	}
	
}