package br.senac.bluhelp.dto.user;

public record UserDTO(Long id, String name, String surname, String password, String cpf, byte[] photo, String phone, String email) {
}