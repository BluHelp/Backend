package br.senac.bluhelp.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.model.user.User;
import br.senac.bluhelp.projection.user.UserProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByCpf(String cpf);

	Optional<UserProjection> findUserById(Long id);

	@Query(value = "SELECT u.id AS id, u.name AS name, u.surname AS surname, u.password AS password, u.cpf AS cpf FROM User as u")
	List<UserProjection> findUsers();
}