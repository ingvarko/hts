package com.hts.restws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hts.entities.BroadcastStreamXML;

/**
 * GET reads a resource 
 * PUT creates a new resource
 * DELETE removes the resources. 
 * POST updates an existing resource or creates a new resource.
 */

@Path("/json/broadcast")
public class BroadcastStreamWsJSON {

	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBroadcastStreamXML(BroadcastStreamXML broadcastStream) {

		String result = "creating broadcast: " + broadcastStream;
		System.out.println(result);
		return Response.status(201).entity(result).build();

	}

	@DELETE
	@Path("{broadcastStreamName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBroadcastStream(@PathParam("broadcastStreamName") String broadcastStreamName) {
		String result = "deleting broadcast: " + broadcastStreamName;

		System.out.println(result);
		return Response.status(201).entity(result).build();

	}

}
