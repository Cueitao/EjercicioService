package com.example.prueba.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.model.Contact;
import com.example.prueba.service.ContactService;

@RestController
public class ContactController {
	
	@Autowired
	ContactService contactService;
//	
//	buscar por email
	@RequestMapping(value= "/rn/contacts/{email}", method = RequestMethod.GET)
	private ResponseEntity<Contact> buscarRn(@PathVariable("email") String email) throws Exception {
		
		return new ResponseEntity<Contact> (contactService.clientBuscarRn(email), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/eloqua/contacts/{email}", method = RequestMethod.GET)
	private  ResponseEntity<Contact> buscarEloqua(@PathVariable("email") String email) throws Exception {
		
		return new ResponseEntity<Contact> (contactService.clientBuscarEloqua(email), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/osc/contacts/{email}", method = RequestMethod.GET)
	private  ResponseEntity<Contact> buscarOsc(@PathVariable("email") String email) throws Exception {
		
		return new ResponseEntity<Contact> (contactService.clientBuscarOsc(email), HttpStatus.OK);

	}
	

//	eliminar
//	---------------------------------------------------------------------------------------------------------------

	@RequestMapping(value= "/rn/contacts/del/{email}", method = {RequestMethod.DELETE})
	public ResponseEntity <String> eliminarRn(@PathVariable("email") String email) throws Exception {

		return new ResponseEntity <String> (contactService.clientEliminarRn(email), HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/eloqua/contacts/del/{email}", method = {RequestMethod.DELETE})
	public ResponseEntity <String> eliminarEloqua(@PathVariable("email") String email) throws Exception {

		return new ResponseEntity <String> (contactService.clientEliminarEloqua(email), HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/osc/contacts/del/{email}", method = {RequestMethod.DELETE})
	public ResponseEntity <String> eliminarOsc(@PathVariable("email") String email) throws Exception {

		return new ResponseEntity <String> (contactService.clientEliminarOsc(email), HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/tres/contacts/del/{email}", method = {RequestMethod.DELETE})
	public  ResponseEntity <String> eliminarTres(@PathVariable("email") String email) throws Exception {

		return new ResponseEntity <String> (contactService.clientEliminarRn(email) + "\n\n"
										  + contactService.clientEliminarEloqua(email) + "\n\n"
										  + contactService.clientEliminarOsc(email), HttpStatus.OK);
		
	}
	
//	crear
//	-----------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping(value= "/rn/contacts/p", method = {RequestMethod.POST})
	public ResponseEntity <String> crearRn(@RequestBody String json) throws Exception {
		return new ResponseEntity <String> (contactService.convertObjectRn(json), HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping(value= "/eloqua/contacts/p", method = {RequestMethod.POST})
	public ResponseEntity <String> crearEloqua(@RequestBody String json) throws Exception {		
		return new ResponseEntity <String> (contactService.convertObjectEloqua(json), HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping(value= "/osc/contacts/p", method = {RequestMethod.POST})
	public ResponseEntity <String> crearOsc(@RequestBody String json) throws Exception {	
		return new ResponseEntity <String> (contactService.convertObjectOsc(json), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value= "/tres/contacts/p", method = {RequestMethod.POST})
	public ResponseEntity <String> crearTodos(@RequestBody String json) throws Exception {	
		return new ResponseEntity <String> ( contactService.convertObjectRn(json) + "\n" 
										   + contactService.convertObjectEloqua(json) + "\n" 
										   + contactService.convertObjectOsc(json), HttpStatus.ACCEPTED);
	}
}
