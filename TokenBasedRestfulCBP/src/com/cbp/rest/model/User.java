package com.cbp.rest.model;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class User implements JsonSerializable {
	
	// global unique identifier
	private String id = null;
	private String username = null;
	private String firstname = null;
	private String lastname = null;

	public User() {}
	
	public User( String username, String firstname, String lastname ) {			
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public User( String id, String email, String firstname, String lastname ) {	
		this.id = id;
		this.username = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}

	@Override
	public JSONObject toJson() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put( "username", this.username );
		jsonObject.put( "firstname", this.firstname );
		jsonObject.put( "lastname", this.lastname );
		return jsonObject;
	}
	
}
