package br.senac.bluhelp.dto.user;

import java.util.List;

import br.senac.bluhelp.dto.project.ProjectQueryDTO;

public record UserProjectionDTO(Long id, String name, String surname, String cpf, byte[] photo, String phone, String email, List<ProjectQueryDTO> projects) {

}
