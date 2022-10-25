package br.senac.bluhelp.dto.user;

import java.util.List;

import br.senac.bluhelp.dto.project.ProjectQueryDTO;

public record UserProfileDTO(Long id, String name, String surname, String cpf, byte[] photo, String phone, String email, List<ProjectQueryDTO> createdProjects, List<ProjectQueryDTO> contributedProjects) {

}
