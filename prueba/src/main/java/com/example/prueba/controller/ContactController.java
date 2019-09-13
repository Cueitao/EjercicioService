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
		
		return new ResponseEntity<Contact> (contactService.buscarRn(email), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/eloqua/contacts/{email}", method = RequestMethod.GET)
	private  ResponseEntity<Contact> buscarEloqua(@PathVariable("email") String email) throws Exception {
		
		return new ResponseEntity<Contact> (contactService.buscarEloqua(email), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/osc/contacts/{email}", method = RequestMethod.GET)
	private  ResponseEntity<Contact> buscarOsc(@PathVariable("email") String email) throws Exception {
		
		return new ResponseEntity<Contact> (contactService.buscarOsc(email), HttpStatus.OK);

	}
	

//	eliminar
//	---------------------------------------------------------------------------------------------------------------

	@RequestMapping(value= "/rn/contacts/del/{email}", method = {RequestMethod.DELETE})
	public ResponseEntity <String> eliminarRn(@PathVariable("email") String email) throws Exception {

		return new ResponseEntity <String> (contactService.eliminarRn(email), HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/eloqua/contacts/del/{email}", method = {RequestMethod.DELETE})
	public ResponseEntity <String> eliminarEloqua(@PathVariable("email") String email) throws Exception {

		return new ResponseEntity <String> (contactService.eliminarEloqua(email), HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/osc/contacts/del/{email}", method = {RequestMethod.DELETE})
	public ResponseEntity <String> eliminarOsc(@PathVariable("email") String email) throws Exception {

		return new ResponseEntity <String> (contactService.eliminarOsc(email), HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/tres/contacts/del/{email}", method = {RequestMethod.DELETE})
	public  ResponseEntity <String> eliminarTres(@PathVariable("email") String email) throws Exception {

		return new ResponseEntity <String> (contactService.eliminarRn(email) + "\n\n"
										  + contactService.eliminarEloqua(email) + "\n\n"
										  + contactService.eliminarOsc(email), HttpStatus.OK);
		
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
