package br.senac.bluhelp.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.model.user.User;

import br.senac.bluhelp.projection.user.UserProjection;
import br.senac.bluhelp.projection.user.UserWithContactProjection;
import br.senac.bluhelp.projection.user.UserWithContributedProjectsProjection;
import br.senac.bluhelp.projection.user.UserWithCreatedProjectsProjections;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	
	boolean existsByCpf(String cpf);
	
	Optional <UserProjection> findUserById(Long id);
	
	Optional<UserWithContactProjection> findUserWithContactById(Long id);
	
	Optional<UserWithContributedProjectsProjection> findUserWithContributedProjectById(Long id);
	
	Optional<UserWithCreatedProjectsProjections> findUserWithCreatedProjectById(Long id);
	
	@Query(value ="SELECT u.id AS id, u.name AS name, u.password AS password, u.cpf AS cpf FROM User u")
		List<UserProjection> findUsers();
}