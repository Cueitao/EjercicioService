package com.example.prueba.property;

import java.util.ResourceBundle;


public class GetProperty {
	
	 private static final ResourceBundle PROPERTIES = ResourceBundle.getBundle("application");
	 
	    public static String getRnUrl(){
	        return PROPERTIES.getString("urlRn");
	    }
	    public static String getRnPass(){
	        return PROPERTIES.getString("passRn");
	    }
	    public static String getEloquaUrl(){
	        return PROPERTIES.getString("urlEloqua");
	    }
	    public static String getEloquaPass(){
	        return PROPERTIES.getString("passEloqua");
	    }
	    public static String getOscUrl(){
	        return PROPERTIES.getString("urlOsc");
	    }
	    public static String getOscPass(){
	        return PROPERTIES.getString("passOsc");
	    }
	    public static String getEloquaEmail(){
	        return PROPERTIES.getString("buscarEloqua");
	    }
	    public static String getRnEmail(){
	        return PROPERTIES.getString("buscarRn");
	    }
	    public static String getOscEmail(){
	        return PROPERTIES.getString("buscarOsc");
	    }
	    public static String getOscLead(){
	    	return PROPERTIES.getString("buscarLead");
	    }
	    public static String getBuscarLead(){
	    	return PROPERTIES.getString("buscarLeadEmail");
	    }

}
