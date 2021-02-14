package br.com.stoomtest.address.entity.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import br.com.stoomtest.address.DTO.impl.AddressDTO;
import br.com.stoomtest.address.entity.IEntity;

@Table
@Entity
public class Address implements IEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String streetName;
	private Integer number;
	private String complement;
	private String neighbourhood;
	private String city;
	private String state;
	private String country;
	private String zipcode;

	private Double latitude;
	private Double longitude;

	public Address() {}
	
	public Address(@Valid AddressDTO addressDTO) {
		this.streetName = addressDTO.getStreetName();
		this.number = addressDTO.getNumber();
		this.complement = addressDTO.getComplement();
		this.neighbourhood = addressDTO.getNeighbourhood();
		this.city = addressDTO.getCity();
		this.state = addressDTO.getState();
		this.country = addressDTO.getCountry();
		this.zipcode = addressDTO.getZipcode();
		this.latitude = addressDTO.getLatitude();
		this.longitude = addressDTO.getLongitude();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
