package br.senac.bluhelp.dto.comment;

import java.time.LocalDateTime;

import br.senac.bluhelp.model.comment.Comment;
import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;

public record CommentDTO(Long id, String contents, User user, Project project, LocalDateTime date, Comment referenceComment) {}