package com.example.prueba.model;


public class Contact {
	
	private String id;
	
	private String nombre;
	
	private String apellido;
	
	private String email;
	
	public Contact(String nombre, String apellido, String email) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}
	
	
	public Contact(String id, String nombre, String apellido, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

	public Contact() {
		super();
	}
	

	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Contact [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + "]";
	}

	
	
	
}
