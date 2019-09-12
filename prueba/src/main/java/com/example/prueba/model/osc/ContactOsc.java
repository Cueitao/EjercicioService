package com.example.prueba.model.osc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactOsc {
	
	@JsonProperty("FirstName")
	private String firstName;
	
	@JsonProperty("LastName")
	private String lastName;
	
	@JsonProperty("EmailAddress")
	private String emailAddress;
	
	public ContactOsc() {
		super();
	}

	public ContactOsc(String firstName, String lastName, String emailAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}
	
	@JsonProperty("FirstName")
	public String getFirstName() {
		return firstName;
	}
	
	@JsonProperty("FirstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@JsonProperty("LastName")
	public String getLastName() {
		return lastName;
	}
	
	@JsonProperty("LastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@JsonProperty("EmailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}
	
	@JsonProperty("EmailAddress")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	

	
}
