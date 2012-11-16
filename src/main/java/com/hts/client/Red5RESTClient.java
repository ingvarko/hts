package com.hts.client;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Red5RESTClient {

	// TODO: need to get remote server address somehow
	private final static String BASEURL = "http://localhost:5080/oflaDemo/rest/json/red5";

	private final static Logger log = Logger.getLogger(Red5RESTClient.class);

	public static void main(String[] args) {
		log.info(getAllFlvs());
	}

	public static String getAllFlvs() {
		Client client = Client.create();

		log.info("getAllFlvs ");

		WebResource webResource = null;
		try {
			webResource = client.resource(getBaseURL());
		}
		catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			return null;
		}

		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
		String result = response.getEntity(String.class);

		return result;
	}

	public static String getBaseURL() throws UnsupportedEncodingException {
		return BASEURL;
	}

}
