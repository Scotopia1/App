package com.example.app;

import java.util.Date;

public class Adress {

	private String adressId;
	private String street;
	private String city;
	private String postalCode;
	private String country;

	public Adress(String street, String city, String postalCode, String country) {
		setAdressId();
		setStreet(street);
		setCity(city);
		setPostalCode(postalCode);
		setCountry(country);
	}

	private void setAdressId() {
		Date date = new Date();
		this.adressId = "L" + date.getTime();
	}

	private void setStreet(String street) {
		this.street = street;
	}

	private void setCity(String city) {
		this.city = city;
	}

	private void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	private void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "Adress [street=" + street + ", city=" + city + ", postalCode=" + postalCode + ", country=" + country + "]";
	}
}
