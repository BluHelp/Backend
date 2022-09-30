package br.senac.bluhelp.model.address;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.senac.bluhelp.model.project.Project;

@Entity
@Table(name = "address", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "address_street", "address_number" }) })
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long id;

	@Column(name = "address_street_type", length = 15, nullable = false)
	private String streetType;

	@Column(name = "address_street", length = 60, nullable = false)
	private String street;

	@Column(name = "address_number", nullable = false)
	private short number;

	@Column(name = "address_district", length = 60, nullable = false)
	private String district;

	@Column(name = "address_cep", length = 9, nullable = false)
	private String cep;

	@Column(name = "address_reference", length = 60, nullable = true)
	private String reference;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Project> projects = new ArrayList();

	public Address() {
	}

	public Address(Long id, String streetType, String street, short number, String district, String cep,
			String reference) {
		this.id = id;
		this.streetType = streetType;
		this.street = street;
		this.number = number;
		this.district = district;
		this.cep = cep;
		this.reference = reference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public short getNumber() {
		return number;
	}

	public void setNumber(short number) {
		this.number = number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public void addProjects(Project project) {
		this.projects.add(project);
	}

	public void removeProjects(Project project) {
		this.projects.remove(project);
	}

}