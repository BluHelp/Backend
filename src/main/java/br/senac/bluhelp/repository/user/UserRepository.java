package br.senac.bluhelp.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.bluhelp.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
