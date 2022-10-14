package br.senac.bluhelp.dto.user;

import java.util.List;

import br.senac.bluhelp.dto.project.ProjectQueryDTO;
import br.senac.bluhelp.projection.project.ProjectQueryProjection;

public record UserProfileDTO(Long id, String name, String surname, String cpf, byte[] photo, String phone, String email, List<ProjectQueryProjection> createdProjects, List<ProjectQueryDTO> contributedProjects) {

}
