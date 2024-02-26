package com.restservices.requestdto;

public class UserCreationRequst {
	//{"name":"gowtham","email":"gowthamkosuri1@gmail.com","password":"123456","contact":"9701886575","gender":"male","city":"hyderabad"}

	private String name;
	private String email;
	private String password;
	private long contact;
	private String gender;
	private String city;
	public String getName() {
		return name;
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
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "UserCreationRequst [name=" + name + ", email=" + email + ", password=" + password + ", contact="
				+ contact + ", gender=" + gender + ", city=" + city + "]";
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
