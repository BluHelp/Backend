package br.senac.bluhelp.model.contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.senac.bluhelp.model.user.User;

@Entity
@Table(name = "contact")
public class Contact {

	@Id
	@Column(name = "user_id")
	private Long id;

	@Column(name = "contact_email", length = 45, nullable = false, unique = true)
	private String email;

	@Column(name = "contact_phone", length = 20, nullable = false, unique = true)
	private String phone;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;

	public Contact() {
	}

	public Contact(Long id, String email, String phone, User user) {
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}