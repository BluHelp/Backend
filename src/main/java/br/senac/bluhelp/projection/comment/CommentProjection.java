package br.senac.bluhelp.projection.comment;

import java.time.LocalDateTime;

import br.senac.bluhelp.model.comment.Comment;
import br.senac.bluhelp.model.user.User;

public interface CommentProjection {

	Long getId();
	String getContents();
	User getUser();
	LocalDateTime getDate();
	Comment getReferenceComment();
	
}
