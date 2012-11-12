package com.hts.client;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {

	public static void main(String[] args) {

		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:5080/oflaDemo/rest/json/broadcast/put");

			JSONObject map = new JSONObject();
			map.put("streamName", "JSONStreamName");
			map.put("isActive", true);
			
			String input = map.toString();

			ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

			webResource = client.resource("http://localhost:5080/oflaDemo/rest/json/broadcast/"
					+ URLEncoder.encode("random word £500 <>&amp; bank $", "UTF-8"));

			response = webResource.delete(ClientResponse.class);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			output = response.getEntity(String.class);
			System.out.println(output);

		}
		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
