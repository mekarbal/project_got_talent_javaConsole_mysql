package com.got_talent.models;

public class Admin extends User {

	private String password;

	public Admin(Long id, String first_name, String last_name, String email, String phone, String password) {
		super(id, first_name, last_name, email, phone);
		this.password = password;
	}

	public Admin() {
	}

	@Override
	public String toString() {
		return "Admin [password=" + password + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
