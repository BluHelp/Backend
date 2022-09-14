package br.senac.bluhelp.service.comment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.comment.CommentDTO;
import br.senac.bluhelp.dto.comment.CommentReplyDTO;
import br.senac.bluhelp.exception.comment.CommentNotFoundException;
import br.senac.bluhelp.exception.project.ProjectNotFoundException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.mapper.comment.CommentMapper;
import br.senac.bluhelp.model.comment.Comment;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.projection.comment.CommentProjection;
import br.senac.bluhelp.repository.comment.CommentRepository;
import br.senac.bluhelp.repository.project.ProjectRepository;
import br.senac.bluhelp.repository.user.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;

	public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper,
			UserRepository userRepository, ProjectRepository projectRepository) {
		this.commentRepository = commentRepository;
		this.commentMapper = commentMapper;
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
	}

	public CommentDTO save(CommentDTO commentDTO) {

		User user = userRepository.findById(commentDTO.user())
				.orElseThrow(() -> new UserNotFoundException("User " + commentDTO.user() + " was not found"));
		
		Project project = projectRepository.findById(commentDTO.project())
				.orElseThrow(() -> new ProjectNotFoundException("Project " + commentDTO.project() + " was not found"));

		Comment comment = new Comment();

		comment.setContent(commentDTO.content());
		comment.setUser(user);
		comment.setProject(project);
		comment.setDate(LocalDateTime.now());
		
		user.addContributedProject(project);
		project.addContributors(user);
		project.addComment(comment);

		Comment commentSaved = commentRepository.save(comment);
		
		return commentMapper.toDTO(commentSaved);
	}

	public CommentReplyDTO save(CommentReplyDTO commentReplyDTO) {

		User user = userRepository.findById(commentReplyDTO.user())
				.orElseThrow(() -> new UserNotFoundException("User " + commentReplyDTO.user() + " was not found"));
		
		Project project = projectRepository.findById(commentReplyDTO.project()).orElseThrow(
				() -> new ProjectNotFoundException("Project " + commentReplyDTO.project() + " was not found"));
		
		Comment referencedComment = commentRepository.findById(commentReplyDTO.referencedComment())
				.orElseThrow(() -> new CommentNotFoundException("Comment " + commentReplyDTO.referencedComment() + " was not found"));

		Comment comment = new Comment();

		comment.setContent(commentReplyDTO.content());
		comment.setUser(user);
		comment.setProject(project);
		comment.setDate(LocalDateTime.now());
		comment.setReferencedComment(referencedComment);
		
		user.addContributedProject(project);
		project.addContributors(user);
		project.addComment(comment);

		Comment commentSaved = commentRepository.save(comment);

		return commentMapper.toReplyDTO(commentSaved);
	}

	public void update(Long id, CommentDTO commentDTO) {

		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new CommentNotFoundException("Comment " + id + " was not found"));

		comment.setContent(commentDTO.content());

		commentRepository.save(comment);
	}
	
	public void update(Long id, CommentReplyDTO commentReplyDTO) {
		
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new CommentNotFoundException("Comment " + id + " was not found"));

		comment.setContent(commentReplyDTO.content());

		commentRepository.save(comment);
	}

	public void delete(Long id) {

		if (!commentRepository.existsById(id)) {
			throw new CommentNotFoundException("Comment " + id + " was not found");
		}

		commentRepository.deleteById(id);
	}

	public CommentProjection findById(Long id) {

		CommentProjection comment = commentRepository.findCommentById(id)
				.orElseThrow(() -> new CommentNotFoundException("Comment " + id + " was not found"));

		return comment;
	}

	public List<CommentProjection> findAll() {
		return commentRepository.findComments();
	}

}