package br.senac.bluhelp.dto.review;

import java.time.LocalDateTime;

import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;

public record ReviewDTO(Long id, byte grade, User user, Project project, LocalDateTime date) {}