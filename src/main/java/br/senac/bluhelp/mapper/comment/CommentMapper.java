package br.senac.bluhelp.mapper.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.comment.CommentDTO;
import br.senac.bluhelp.exception.comment.CommentNotFoundException;
import br.senac.bluhelp.exception.project.ProjectNotFoundException;
import br.senac.bluhelp.exception.user.UserNotFoundException;
import br.senac.bluhelp.model.comment.Comment;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.repository.comment.CommentRepository;
import br.senac.bluhelp.repository.project.ProjectRepository;
import br.senac.bluhelp.repository.user.UserRepository;

@Service
public class CommentMapper {

	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final CommentRepository commentRepository;

	public CommentMapper(UserRepository userRepository, ProjectRepository projectRepository,
			CommentRepository commentRepository) {
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
		this.commentRepository = commentRepository;
	}

	public CommentDTO toDTO(Comment comment) {
		return new CommentDTO(comment.getId(), comment.getContent(), comment.getUser().getId(),
				comment.getProject().getId(), comment.getDate(), comment.getReferencedComment().getId());
	}

	public List<CommentDTO> toDTO(List<Comment> comments) {

		final List<CommentDTO> commentsDTO = new ArrayList<>();

		for (Comment comment : comments) {
			commentsDTO.add(toDTO(comment));
		}

		return commentsDTO;
	}

	public Comment toEntity(CommentDTO commentDTO) {

		User user = userRepository.findById(commentDTO.user())
				.orElseThrow(() -> new UserNotFoundException("User " + commentDTO.user() + " was not found"));

		Project project = projectRepository.findById(commentDTO.project())
				.orElseThrow(() -> new ProjectNotFoundException("Project " + commentDTO.project() + " was not found"));

		Comment referencedComment = commentRepository.findById(commentDTO.referencedComment()).orElseThrow(
				() -> new CommentNotFoundException("Comment " + commentDTO.referencedComment() + " was not found"));

		return new Comment(commentDTO.id(), commentDTO.content(), user, project, commentDTO.date(), referencedComment);
	}

}
