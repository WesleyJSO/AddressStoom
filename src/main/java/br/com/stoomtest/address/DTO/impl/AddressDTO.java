package br.com.stoomtest.address.DTO.impl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.stoomtest.address.DTO.IDTO;

public class AddressDTO implements IDTO {
	
	@NotNull @NotBlank private String streetName;
	@NotNull @NotBlank private Integer number;
	private String complement;
	@NotNull @NotBlank private String neighbourhood;
	@NotNull @NotBlank private String city;
	@NotNull @NotBlank private String state;
	@NotNull @NotBlank private String country;
	@NotNull @NotBlank private String zipcode;
	
	private Double latitude;
	private Double longitude;
	
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
