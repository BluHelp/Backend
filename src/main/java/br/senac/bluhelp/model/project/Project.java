package br.senac.bluhelp.model.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.senac.bluhelp.enumeration.progress.Progress;
import br.senac.bluhelp.model.address.Address;
import br.senac.bluhelp.model.category.Category;
import br.senac.bluhelp.model.comment.Comment;
import br.senac.bluhelp.model.review.Review;
import br.senac.bluhelp.model.user.User;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User creator;

	@Column(name = "project_title", length = 45, nullable = false, unique = false)
	private String title;

	@Column(name = "project_objective", length = 150, nullable = false, unique = false)
	private String objective;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(name = "project_description", length = 500, nullable = false, unique = false)
	private String description;
	
	@Column(name = "project_date")
	private LocalDateTime date;

	@ManyToMany(mappedBy = "contributedProjects")
	private List<User> contributors;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews;

	@Enumerated(EnumType.ORDINAL)
	private Progress progress;
	
	@ManyToMany(mappedBy = "projects")
	private List<Category> categories = new ArrayList<>();
	
	@Lob
	@Column(name = "project_photo")
	private byte[] photo;

	public Project() {
	}

	public Project(Long id, User creator, String title, String objective, Address address, String description, LocalDateTime date,
			 Progress progress, byte[] photo) {
		this.id = id;
		this.creator = creator;
		this.title = title;
		this.objective = objective;
		this.address = address;
		this.description = description;
		this.date = date;
		contributors = new ArrayList<>();
		comments = new ArrayList<>();
		reviews = new ArrayList<>();
		this.progress = progress;
		this.photo = photo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public List<User> getContributors() {
		return contributors;
	}

	public void addContributors(User user) {
		this.contributors.add(user);
	}

	public void removeContributors(User user) {
		this.contributors.remove(user);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}

	public List<Review> getReview() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void addCategory(Category category) {
		this.categories.add(category);
	}
	
	public void removeCategory(Category category) {
		this.categories.remove(category);
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
}