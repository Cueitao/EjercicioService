package com.example.prueba.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.prueba.model.Contact;
import com.example.prueba.model.eloqua.ContactEloqua;
import com.example.prueba.model.lead.Lead;
import com.example.prueba.model.osc.ContactOsc;
import com.example.prueba.model.rightNow.AddressType;
import com.example.prueba.model.rightNow.ContactRn;
import com.example.prueba.model.rightNow.Emails;
import com.example.prueba.model.rightNow.Name;
import com.example.prueba.property.GetProperty;
import com.example.prueba.metodo.Metodo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ContactService {
	
//	OSvC/RightNow
//--------------------------------------------------------------------------------------------------------------------
	public Contact buscarRn(String email) throws Exception {
		Contact c1 = new Contact();	 
		String conte = Metodo.metGet(GetProperty.getRnEmail(), Metodo.authRn, email);	
		JSONObject jsonObject = new JSONObject(conte);
		JSONArray item = (JSONArray) jsonObject.get("items");
		System.out.println(item);

			if(item != null && item.length() > 0 ){
				
//				Hacer split para separar el nombre del apellido del lookupName
				String lookupName = item.getJSONObject(0).getString("lookupName");
				String[] splitName = lookupName.split(" ", 0);
			
				String nom = splitName[0];
				String ape = splitName[1];
					
				c1.setId(Long.toString(item.getJSONObject(0).getLong("id")));
				c1.setNombre(nom);
				c1.setApellido(ape);
				c1.setEmail(email);
					
				return c1;
			}
		
			else return c1; 
	}
			
	public String eliminarRn(String email) throws Exception {
		Contact datos = buscarRn(email);
				
			if(datos.getId()==null)
				return "El contacto no existe en OSvC";
		
			else {
				Metodo.metDelete(GetProperty.getRnUrl(), Metodo.authRn, datos.getId());	
					
				return "Eliminado el contacto de OSvC con"
												+ "\n id: " + datos.getId()
												+ "\n nombre: " + datos.getNombre() 
												+ "\n apellido: " + datos.getApellido()
												+ "\n email: " + datos.getEmail();
			}
	}
				
	public String crearRn(String json) throws Exception {
				
			JSONObject jsonObject = new JSONObject(json);
			JSONObject emails =(JSONObject) jsonObject.get("emails");
			String address = emails.getString("address");

			Contact cont = buscarRn(address);
				
				if(cont.getId()!=null)
					return "El email: " + address + " ya existe en OSvC";
				
				else 

					return Metodo.metPost(GetProperty.getRnUrl(), Metodo.authRn,json) + " OSvC";
				
	}
			
	public String convertObjectRn(String json) {
		JSONObject jsonObject = new JSONObject(json);
		ContactRn contactRn = new ContactRn();
		contactRn.setName(new Name (Metodo.transformarLetras(jsonObject.getString("first")), 
									Metodo.transformarLetras(jsonObject.getString("last"))));
		contactRn.setEmails(new Emails (Metodo.transformarLetras(jsonObject.getString("address")), new AddressType(0)));
			try {
				String contactJSON = new ObjectMapper().writeValueAsString(contactRn);
				return crearRn(contactJSON);
			}
			catch (Exception e) {
				e.printStackTrace();
				return json;
			}
	}
			
//	Eloqua
//--------------------------------------------------------------------------------------------------------------------
			
	public Contact buscarEloqua(String email) throws Exception {
		Contact c1 = new Contact();
				
		String content = Metodo.metGet(GetProperty.getEloquaEmail(), Metodo.authEloqua, email);
						
		JSONObject jsonObject = new JSONObject(content);
		JSONArray element = (JSONArray) jsonObject.get("elements");

			if(element != null && element.length() > 0 ){
				
//				buscar por id, para coger nombre y apellido	
				String content2 = Metodo.metGetId(GetProperty.getEloquaUrl(), Metodo.authEloqua, element.getJSONObject(0).getString("id"));
				JSONObject jsonObject2 = new JSONObject(content2);
			
				c1.setId(element.getJSONObject(0).getString("id"));
				c1.setNombre(jsonObject2.getString("firstName"));
				c1.setApellido(jsonObject2.getString("lastName"));
				c1.setEmail(email);
				
				return c1;
			}
			
			else 
				return c1;
			}
			
	public String eliminarEloqua(String email) throws Exception {
					
		Contact datos = buscarEloqua(email);
				
			if(datos.getId()==null)
				return "El contacto no existe en Eloqua";
			
			else {
				Metodo.metDelete(GetProperty.getEloquaUrl(), Metodo.authEloqua, datos.getId());	
					
				return "Eliminado el contacto de Eloqua con"
															+ "\nid: " + datos.getId()
															+ "\nnombre: " + datos.getNombre() 
															+ "\napellido: " + datos.getApellido()
															+ "\nemail: " + datos.getEmail();
			}
	}
			
	public String crearEloqua(String json) throws Exception {
		
		JSONObject jsonObject = new JSONObject(json);

		String emails = jsonObject.getString("emailAddress");

		Contact cont = buscarEloqua(emails);
				
			if(cont.getId()!=null)
				return "El email: " + emails + " ya existe en Eloqua";
		
			else {
				return Metodo.metPost(GetProperty.getEloquaUrl(),Metodo.authEloqua,json) + " Eloqua";
			}
				
	}
			
	public String convertObjectEloqua(String json) {
		JSONObject jsonObject = new JSONObject(json);
		ContactEloqua contactEloqua = new ContactEloqua();
		contactEloqua.setFirstName(Metodo.transformarLetras(jsonObject.getString("first")));
		contactEloqua.setLastName(Metodo.transformarLetras(jsonObject.getString("last")));
		contactEloqua.setEmailAddress(Metodo.transformarLetras(jsonObject.getString("address")));
			try {
				String contactJSON = new ObjectMapper().writeValueAsString(contactEloqua);
				return crearEloqua(contactJSON);
			}
			
			catch (Exception e) {
				e.printStackTrace();
				return json;
			}
				
	}
			
//	OSC
//--------------------------------------------------------------------------------------------------------------------
	public Contact buscarOsc(String email) throws Exception {
		Contact c1 = new Contact();
				
		String content = Metodo.metGet(GetProperty.getOscEmail(), Metodo.authOsc,email);

		JSONObject jsonObject = new JSONObject(content);
		JSONArray item = (JSONArray) jsonObject.get("items");
			if(item != null && item.length() > 0 ){
					
				c1.setId(item.getJSONObject(0).getString("PartyNumber"));
				c1.setNombre(item.getJSONObject(0).getString("FirstName"));
				c1.setApellido(item.getJSONObject(0).getString("LastName"));
				c1.setEmail(email);
				return c1;
			}
			
			else return c1;
	}
			

	public String eliminarOsc(String email) throws Exception {
				
		Contact datos = buscarOsc(email);
				
		if(datos.getId()==null)
			
			return "El contacto no existe en OSC";
		
		else {
			
			String resLead = eliminarLead(email);
			Metodo.metDelete(GetProperty.getOscUrl(), Metodo.authOsc, datos.getId());
			return "Eliminado el contacto de OSC con"
													+ "\nid: " + datos.getId()
													+ "\nnombre: " + datos.getNombre()
													+ "\napellido: " + datos.getApellido()
													+ "\nemail: " + datos.getEmail()
					+ "\nY " + resLead;
		}
	}
			
	public String crearOsc(String json) throws Exception {
				
		JSONObject jsonObject = new JSONObject(json);

		String emails = jsonObject.getString("EmailAddress");

		Contact cont = buscarOsc(emails);

			if(cont.getId()!=null)
				
				return "El email: " + emails + " ya existe en OSC";
			
			else  	{
					
					String result = Metodo.metPost(GetProperty.getOscUrl(), Metodo.authOsc, json) + " OSC";
					convertObjectLeadOsc(json);
					return result;
			}
	}
	
	public String convertObjectOsc(String json) {
		
		JSONObject jsonObject = new JSONObject(json);
		ContactOsc contactOsc = new ContactOsc();
		
		contactOsc.setFirstName(Metodo.transformarLetras(jsonObject.getString("first")));
		contactOsc.setLastName(Metodo.transformarLetras(jsonObject.getString("last")));
		contactOsc.setEmailAddress(Metodo.transformarLetras(jsonObject.getString("address")));
		
			try {
				String contactJSON = new ObjectMapper().writeValueAsString(contactOsc);
				String result = crearOsc(contactJSON);				
				return result;
			}
			catch (Exception e) {
				e.printStackTrace();
				return json;
			}
	}
	
	
//	Lead
//----------------------------------------------------------------------------------------------------------
	public String convertObjectLeadOsc(String json) throws Exception {
		
		JSONObject jsonObject = new JSONObject(json);
		Contact c1 = buscarOsc(jsonObject.getString("EmailAddress"));
		Lead lead = new Lead();
		
		lead.setName(Metodo.transformarLetras(c1.getNombre() + " " + Metodo.transformarLetras(c1.getApellido())));
		lead.setContactPartyNumber(c1.getId());
		
			try {
				String leadJSON = new ObjectMapper().writeValueAsString(lead);
				String crearLead = Metodo.metPost(GetProperty.getOscLead(), Metodo.authOsc, leadJSON);
				return crearLead;
			}
			catch (Exception e) {
				e.printStackTrace();
				return json;
			}
	}
	
	public String eliminarLead(String email) throws Exception{
		
		String content = Metodo.metGet(GetProperty.getBuscarLead(), Metodo.authOsc, email);
		
		JSONObject jsonObject = new JSONObject(content);
		JSONArray item = (JSONArray) jsonObject.get("items");
		
			if(item != null && item.length() > 0 ){
				
				for (int i = 0; i<item.length(); i++) {
					
					Long longId = item.getJSONObject(i).getLong("LeadId");
					String leadId = String.valueOf(longId);
					
					Metodo.metDelete(GetProperty.getOscLead(), Metodo.authOsc, leadId);
				}
				return "los Leads asociados";
			}
			
			else return "Error en la eliminación de los Leads";
	
	}
}

			