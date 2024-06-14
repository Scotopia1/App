package com.example.app;

import java.util.Date;

public class Address {

	private String addressId;
	private String street;
	private String city;
	private String postalCode;
	private String country;

	public Address(String street, String city, String postalCode, String country) {
		setAddressId();
		setStreet(street);
		setCity(city);
		setPostalCode(postalCode);
		setCountry(country);
	}

	public Address(String DeliveryAddress) {
		setAddressId();
		String[] address = DeliveryAddress.split(",");
		setStreet(address[0]);
		setCity(address[1]);
		setPostalCode(address[2]);
		setCountry(address[3]);
	}

	private void setAddressId() {
		Date date = new Date();
		this.addressId = "L" + date.getTime();
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
		return "Address [street=" + street + ", city=" + city + ", postalCode=" + postalCode + ", country=" + country + "]";
	}
}
