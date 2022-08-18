package br.senac.bluhelp.dto.contact;

import br.senac.bluhelp.model.user.User;

public record ContactDTO (Long id, String email, String phone, User user) {

}
