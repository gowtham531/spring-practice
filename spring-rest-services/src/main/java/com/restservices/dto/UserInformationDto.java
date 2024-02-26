package com.restservices.dto;

public class UserInformationDto {
	
	private String name;
	private String email;
	private String password;
	private String contact;
	private String gender;
	private String city;
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "UserInformationDto [name=" + name + ", email=" + email + ", password=" + password + ", contact="
				+ contact + ", gender=" + gender + ", city=" + city + "]";
	}
	public UserInformationDto(String name, String email, String password, String contact, String gender, String city) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.gender = gender;
		this.city = city;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
