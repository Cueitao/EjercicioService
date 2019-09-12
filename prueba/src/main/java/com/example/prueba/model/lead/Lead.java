package com.example.prueba.model.lead;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lead {
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("ContactPartyNumber")
	private String contactPartyNumber;
	
	
	public Lead() {
		super();
	}

	public Lead(String name, String contactPartyNumber) {
		super();
		this.name = name;
		this.contactPartyNumber = contactPartyNumber;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("Name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("ContactPartyNumber")
	public String getContactPartyNumber() {
		return contactPartyNumber;
	}
	
	@JsonProperty("ContactPartyNumber")
	public void setContactPartyNumber(String contactPartyNumber) {
		this.contactPartyNumber = contactPartyNumber;
	}
	
	

}
