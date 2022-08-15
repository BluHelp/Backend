package br.senac.bluhelp.mapper.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.comment.CommentDTO;
import br.senac.bluhelp.model.comment.Comment;

@Service
public class CommentMapper {
	
	public CommentDTO toDTO(Comment comment) {
		return new CommentDTO(comment.getId(), comment.getContents(), comment.getUser(), comment.getProject(), comment.getDate(), comment.getReferenceComment());
	}
	
	public List<CommentDTO> toDTO(List<Comment> comments){
		
		final List<CommentDTO> commentsDTO = new ArrayList<>();
		
		for(Comment comment : comments) {
			commentsDTO.add(toDTO(comment));
		}
		
		return commentsDTO;
	}
	
	public Comment toEntity(CommentDTO commentDTO) {
		return new Comment(commentDTO.id(), commentDTO.contents(), commentDTO.user(), commentDTO.project(), commentDTO.date(), commentDTO.referenceComment());
	}

}