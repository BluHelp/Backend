package br.senac.bluhelp.dto.project;

import br.senac.bluhelp.enumeration.category.Category;
import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.user.User;

public record ProjectDTO(Long id, User creator, String title, String objective, Address address,
		String projectDescription, Progress progress, Category category) {

}
