package src.main.java.br.senac.bluhelp.dto.project;

public record ProjectDTO(Long id, User creator, String title, String objective, Address address,
		String projectDescription, Progress progress) {

}
