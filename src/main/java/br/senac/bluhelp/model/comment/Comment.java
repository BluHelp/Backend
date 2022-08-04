package br.senac.bluhelp.model.comment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.senac.bluhelp.model.project.Project;
import br.senac.bluhelp.model.user.User;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;

	@Column(name = "comment_contents", length = 300, nullable = false, unique = false)
	private String contents;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	@Column(name = "comment_date", nullable = false)
	private LocalDateTime date;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reference_comment_id")
	private Comment referenceComment;

	public Comment() {
	}

	public Comment(Long id, String contents, User user, Project project, LocalDateTime date, Comment referenceComment) {
		this.id = id;
		this.contents = contents;
		this.user = user;
		this.project = project;
		this.date = date;
		this.referenceComment = referenceComment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Comment getReferenceComment() {
		return referenceComment;
	}

	public void setReferenceComment(Comment referenceComment) {
		this.referenceComment = referenceComment;
	}
}