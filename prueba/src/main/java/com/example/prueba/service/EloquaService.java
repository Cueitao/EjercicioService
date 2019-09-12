package com.example.prueba.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class EloquaService {

	private final String USER_AGENT = "Mozilla/5.0";
//	private String authString = "blin" + ":" + "Soaint2019";
//	private String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));

	public String sendGet(long id) throws Exception {
		

		String url = "https://soaintcrp.custhelp.com/api/REST/1.0/data/contact/" + id + "?depth=minimal";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		//con.setRequestProperty("Authorization","Basic " + authStringEnc);


		//int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		return response.toString();
		

	}
	
}
