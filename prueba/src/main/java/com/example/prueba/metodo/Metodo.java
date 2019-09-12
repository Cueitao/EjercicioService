package com.example.prueba.metodo;

import java.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.example.prueba.property.GetProperty;

public class Metodo {
	
	public static String authRn = "Basic " + new String(Base64.getEncoder().encode(GetProperty.getRnPass().getBytes()));
	public static String authEloqua = "Basic " + new String(Base64.getEncoder().encode(GetProperty.getEloquaPass().getBytes()));
	public static String authOsc = "Basic " + new String(Base64.getEncoder().encode(GetProperty.getOscPass().getBytes()));

	public static String metGet(String url, String auth, String email) throws Exception {
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url + "'" + email + "'");
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, auth);
		HttpResponse response = httpClient.execute(httpGet);
//		
//		int statusCode = response.getStatusLine().getStatusCode();
//		System.out.println(statusCode);
		
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity);
		httpClient.close();
		return content;
	}
	
	public static void metDelete(String url, String auth, String id) throws Exception {
		
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpDelete httpDelete = new HttpDelete(url + "/" + id);
		
		httpDelete.setHeader(HttpHeaders.AUTHORIZATION, auth);

		httpclient.execute(httpDelete);
		httpclient.close();
	}
	
	public static String metPost(String url, String auth, String json) throws Exception {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(HttpHeaders.AUTHORIZATION, auth);
		httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
		
	    StringEntity stringEntity = new StringEntity(json);
		httpPost.setEntity(stringEntity);
		HttpResponse response = httpClient.execute(httpPost);
		
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode !=201) 
			return "Error de caracteres";
		httpClient.close();
		return "El contacto se ha creado con exito";
	}
	
	public static String transformarLetras(String jsonSend) {
        jsonSend = jsonSend.replace("ñ", "n").replace("Ñ", "N")
        .replace("Á", "A").replace("á", "a").replace("É", "E").replace("é", "e").replace("Í", "I").replace("í", "i").replace("Ó", "O").replace("ó", "o").replace("Ú", "U").replace("ú", "u")
        .replace("Ä", "A").replace("ä", "a").replace("Ë", "E").replace("ë", "e").replace("Ï", "I").replace("ï", "i").replace("Ö", "O").replace("ö", "o").replace("Ü", "U").replace("ü", "u");
        return jsonSend;
    }
	
}
