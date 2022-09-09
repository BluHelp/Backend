package br.senac.bluhelp.service.comment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.comment.CommentDTO;
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
		
		Comment comment = commentMapper.toEntity(commentDTO);
		
		comment.setDate(LocalDateTime.now());
		
		Comment commentSaved = commentRepository.save(comment);
		
		return commentMapper.toDTO(commentSaved);
	}

	public void update(Long id, CommentDTO commentDTO) {
		
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment " + id + " was not found"));
		
		User user = userRepository.findById(commentDTO.user())
				.orElseThrow(() -> new UserNotFoundException("User " + commentDTO.user() + " was not found"));
		
		Project project = projectRepository.findById(commentDTO.project())
				.orElseThrow(() -> new ProjectNotFoundException("Project " + commentDTO.project() + " was not found"));

		Comment referencedComment = commentRepository.findById(commentDTO.referencedComment()).orElseThrow(
				() -> new CommentNotFoundException("Comment " + commentDTO.referencedComment() + " was not found"));
		
		comment.setContent(commentDTO.content());
		comment.setUser(user);
		comment.setProject(project);
		comment.setDate(commentDTO.date());
		comment.setReferencedComment(referencedComment);
		
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