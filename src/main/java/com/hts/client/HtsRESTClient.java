package com.hts.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HtsRESTClient {

	// TODO: need to get remote server address somehow
	private final static String BASEURL = "http://localhost:5080/oflaDemo/rest/json/broadcast";

	private final static Logger log = Logger.getLogger(HtsRESTClient.class);

	public static Boolean checkAccess(String broadcastStreamName, String ipAddress) {
		Client client = Client.create();
		
		log.info("Checking access for " + ipAddress + " to " + broadcastStreamName);
		
		WebResource webResource = null;
		try {
			webResource = client
					.resource(getBaseURL(broadcastStreamName) + "/" + URLEncoder.encode(ipAddress, "UTF-8"));
		}
		catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			return null;
		}

		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);

		int status = response.getStatus();
		switch (status) {
		case 201:
			log.info("Access allowed");
			return true;
		case 403:
			log.info("Access allowed");
			return false;
		default:
			log.error("Failed : HTTP error code : " + status);
			throw new RuntimeException("Failed : HTTP error code : " + status);
		}

	}

	public static String create(String broadcastStreamName) {
		Client client = Client.create();

		WebResource webResource = null;
		try {
			webResource = client.resource(getBaseURL() + "/put");
		}
		catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			return null;
		}

		JSONObject map = new JSONObject();
		map.put("streamName", broadcastStreamName);
		map.put("active", true); // not necessary. Set true in BroadcasStream constructor;

		String input = map.toString();

		ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);
		log.info("[WS] BroadcastStreamClient: " + output);

		return output;
	}

	public static String unregister(String broadcastStreamName) {
		WebResource webResource = null;

		Client client = Client.create();

		try {
			webResource = client.resource(getBaseURL(broadcastStreamName));
		}
		catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			return null;
		}
		ClientResponse response = webResource.delete(ClientResponse.class);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);
		log.info(output);
		return output;

	}

	public static String unregisterAll() {
		WebResource webResource = null;

		Client client = Client.create();

		try {
			webResource = client.resource(getBaseURL());
		}
		catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			return null;
		}
		ClientResponse response = webResource.delete(ClientResponse.class);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);
		log.info(output);
		return output;

	}

	public static void main(String[] args) {
		create("JSONStreamName");
		unregisterAll();
		create("JSONStreamName");
		checkAccess("JSONStreamName", "222.222.222.222");
		unregister("JSONStreamName");
	}

	public static String getBaseURL() throws UnsupportedEncodingException {
		return BASEURL;
	}

	/**
	 * 
	 * @param stringToEncode - using URLEncoder.encode(stringToEncode, "UTF-8");
	 * @return BASEURL +"/"+ URLEncoder.encode(stringToEncode, "UTF-8");
	 * @throws UnsupportedEncodingException
	 */
	public static String getBaseURL(String stringToEncode) throws UnsupportedEncodingException {
		return BASEURL + "/" + URLEncoder.encode(stringToEncode, "UTF-8");
	}
}
