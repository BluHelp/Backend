package br.senac.bluhelp.repository.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.model.comment.Comment;
import br.senac.bluhelp.projection.comment.CommentProjection;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	boolean existsById(Long id);
	
	Optional <CommentProjection> findCommentById(Long id);
	
	@Query(value= "SELECT c.id AS id, c.user AS user, c.content AS content, c.date AS date FROM Comment as c")
	List<CommentProjection> findComments();
	
}