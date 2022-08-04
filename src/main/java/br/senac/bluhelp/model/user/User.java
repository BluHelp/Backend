package br.senac.bluhelp.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.senac.bluhelp.model.project.Project;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "user_name", length = 45, nullable = false, unique = false)
	private String name;

	@Column(name = "user_password", length = 30, nullable = false, unique = false)
	private String password;

	@Column(name = "user_cpf", length = 14, nullable = false, unique = true)
	private String cpf;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Project> createdProjects;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "project_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> contributedProjects;

	public User() {
	}

	public User(Long id, String name, String password, String cpf) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.cpf = cpf;
		this.createdProjects = new ArrayList<>();
		this.contributedProjects = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Project> getCreatedProjects() {
		return createdProjects;
	}

	public void addCreatedProject(Project project) {
		this.createdProjects.add(project);
	}

	public void removeCreatedProject(Project project) {
		this.createdProjects.remove(project);
	}

	public List<Project> getContributedProjects() {
		return contributedProjects;
	}

	public void addContributedProject(Project project) {
		this.contributedProjects.add(project);
	}

	public void removeContributedProject(Project project) {
		this.contributedProjects.remove(project);
	}
}