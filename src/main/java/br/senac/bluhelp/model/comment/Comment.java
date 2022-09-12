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

	@Column(name = "comment_content", length = 300, nullable = false, unique = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@Column(name = "comment_date")
	private LocalDateTime date;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "referenced_comment_id")
	private Comment referencedComment;

	public Comment() {
	}

	public Comment(Long id, String content, User user, Project project, LocalDateTime date, Comment referencedComment) {
		this.id = id;
		this.content = content;
		this.user = user;
		this.project = project;
		this.date = date;
		this.referencedComment = referencedComment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Comment getReferencedComment() {
		return referencedComment;
	}

	public void setReferencedComment(Comment referencedComment) {
		this.referencedComment = referencedComment;
	}
}