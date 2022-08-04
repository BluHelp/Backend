package br.senac.bluhelp.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "address", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "address_street", "address_numero" }) })
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long id;

	@Column(name = "address_road_type", length = 15, nullable = false)
	private String roadType;

	@Column(name = "address_street", length = 60, nullable = false)
	private String street;

	@Column(name = "address_number", nullable = false)
	private short number;

	@Column(name = "address_neighborhood", length = 60, nullable = false)
	private String neighborhood;

	@Column(name = "address_complement", length = 60, nullable = true)
	private String complement;

	public Address(Long id, String roadType, String street, short number, String neighborhood, String complement) {
		this.id = id;
		this.roadType = roadType;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.complement = complement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
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

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}
}