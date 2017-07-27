package diegoLibraries.mail;

import java.util.HashMap;

public interface SMTP_Constants {
	String HOTMAIL_HOST="smtp.live.com";
	String GMAIL_HOST="smtp.gmail.com";
	int HOTMAIL_PORT=587;
	int GMAIL_PORT=587;
	
	
	HashMap<String,Integer> defaultPorts=getDefaultMapPorts();


	public static HashMap<String, Integer> getDefaultMapPorts() {
		HashMap<String,Integer> defaultPorts=new HashMap<>();
		defaultPorts.put(HOTMAIL_HOST,HOTMAIL_PORT);
		defaultPorts.put(GMAIL_HOST,GMAIL_PORT);
		return defaultPorts;
	}
	public static int getPort(String host) {
		return defaultPorts.get(host);
	}
	
}
