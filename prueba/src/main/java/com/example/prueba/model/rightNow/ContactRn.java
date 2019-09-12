package com.example.prueba.model.rightNow;

public class ContactRn {
	
	private Name name;
	
	private Emails emails;

	public ContactRn() {
		super();
	}


	public ContactRn(Name name, Emails emails) {
		super();
		this.name = name;
		this.emails = emails;
	}



	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Emails getEmails() {
		return emails;
	}

	public void setEmails(Emails emails) {
		this.emails = emails;
	}

	@Override
	public String toString() {
		return "ContactRn [name=" + name + ", emails=" + emails + "]";
	}

	
}
