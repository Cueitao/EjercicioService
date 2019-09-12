package com.example.prueba.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

import com.example.prueba.property.GetProperty;

public class UrlConnectionService {
	
private final String USER_AGENT = "Mozilla/5.0";
	
	private String authRn = "Basic " + new String(convert(GetProperty.getRnPass()));
	private String authEloqua = "Basic " + new String(convert(GetProperty.getEloquaPass()));
	private String authOsc = "Basic " + new String(convert(GetProperty.getOscPass()));

	
	public String convert(String pass) {
		String passBase64 = new String(Base64.getEncoder().encode(pass.getBytes()));
		return passBase64;
	}

//	OSvC
//	 HTTP GET request
	public String buscarRn(long id) throws Exception {
		
		String url = GetProperty.getRnUrl() + id;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Authorization",authRn);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
		

	}
	
////	 HTTP POST request
	public String crearRn(String nom, String ape) throws Exception {
		
		String url = GetProperty.getRnUrl();
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization",authRn);
		
		con.setDoOutput(true);
		String json = "{ \"name\": {\"first\": \"" + nom  + "\", \"last\": \"" + ape + "\" } }";
		
		OutputStream wr = con.getOutputStream();
		byte[] input = json.getBytes("utf-8");
		wr.write(input, 0, input.length);
//		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//		wr.writeBytes(json);
//		wr.flush();
//		wr.close();
		
		int responseCode = con.getResponseCode();
		return "Creado con exito";

	}
//	
////	 HTTP DELETE request
	public int eliminarRn(long id) throws Exception{

		String url = GetProperty.getRnUrl() + id;
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Authorization","Basic " + authRn);
		
		con.setRequestProperty("Content-Type",
               				"application/x-www-form-urlencoded");
		con.setRequestMethod("DELETE");
		
		int responseCode = con.getResponseCode();
		return responseCode;

	}
	
	
////	Eloqua
////-----------------------------------------------------------------------------------------------------------------		
	public String buscarEloqua(long id) throws Exception {
	
		String url = GetProperty.getEloquaUrl() + id + "?depth=minimal";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Authorization",authEloqua);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
		
	}
	
	public String crearEloqua() throws Exception {

		String url = "https://secure.p03.eloqua.com/API/REST/1.0/data/contact";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization",authEloqua);
		
		con.setDoOutput(true);
		String json = "{\"address1\": \"P.O.Box 72202 - 00200\",\r\n" + 
				"  \"address2\": \"6th Floor, Lonrho House \",\r\n" + 
				"  \"address3\": \"Standard Street, City Centre\",\r\n" + 
				"  \"businessPhone\": \"2540312885\",\r\n" + 
				"  \"city\": \"Copenhagen\",\r\n" + 
				"  \"country\": \"Denmark\",\r\n" + 
				"  \"emailAddress\": \"blin@gmail.com\",\r\n" + 
				"  \"fax\": \"2540312886\",\r\n" + 
				"  \"firstName\": \"Bingbing\",\r\n" + 
				"  \"lastName\": \"Lin\",\r\n" + 
				"  \"mobilePhone\": \"2540312887\",\r\n" + 
				"  \"postalCode\": \"2620\",\r\n" + 
				"  \"province\": \"Malmo\",\r\n" + 
				"  \"salesPerson\": \"Hamlet\",\r\n" + 
				"  \"title\": \"Actor\",\r\n" + 
				"  \"fieldValues\": [\r\n" + 
				"    {\r\n" + 
				"      \"id\": \"100017\",\r\n" + 
				"      \"value\": \"Mr.\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"id\": \"100044\",\r\n" + 
				"      \"value\": \"source-integration\"\r\n" + 
				"    }\r\n" + 
				"  ]}";
		
		OutputStream wr = con.getOutputStream();
		byte[] input = json.getBytes("utf-8");
		wr.write(input, 0, input.length);
		
		int responseCode = con.getResponseCode();
		return "Contacto creado";
		
	}
	
	public int eliminarEloquoa(long id) throws Exception{

		String url = GetProperty.getEloquaUrl() + id;
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Authorization",authEloqua);
		
//		con.setRequestProperty("Content-Type",
//               				"application/json");
		con.setRequestMethod("DELETE");
		
		int responseCode = con.getResponseCode();
		return responseCode;

	}
    
//	
////	OSC
////-----------------------------------------------------------------------------------------------------------------
	public String buscarOsc(long id) throws Exception {

		String url = GetProperty.getOscUrl() + id;
			
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Authorization",authOsc);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
		
	}
	
	public int crearOsc(String nom, String ape) throws Exception {
		
		String url = GetProperty.getOscUrl();
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization",authOsc);
		
		con.setDoOutput(true);
		String json = "{\"firstName\": \"" + nom + "\", "
								+ "\"lastName\": \"" + ape + "\" }";
		
		OutputStream wr = con.getOutputStream();
		byte[] input = json.getBytes("utf-8");
		wr.write(input, 0, input.length);
		
		int responseCode = con.getResponseCode();
		return responseCode;
		
	}
	
	public int eliminarOsc(long id) throws Exception{

		String url = GetProperty.getOscUrl() + id;
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Authorization",authOsc);
		
//		con.setRequestProperty("Content-Type",
//               				"application/json");
		con.setRequestMethod("DELETE");
		
		int responseCode = con.getResponseCode();
		return responseCode;

	}
}
