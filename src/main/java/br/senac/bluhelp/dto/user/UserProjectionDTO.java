package br.senac.bluhelp.dto.user;

import java.util.List;

import br.senac.bluhelp.projection.project.ProjectQueryProjection;

public record UserProjectionDTO(Long id, String name, String surname, String password, String cpf, byte[] photo, String phone, String email, List<ProjectQueryProjection> createdProjects, List<ProjectQueryProjection> coontributedProjects ) {

}
