package com.cbp.rest.model;

public class Credentials {
	private String username = null;
	private String password = null;
	
	public Credentials() {}
	
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String email) {
		this.username = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
