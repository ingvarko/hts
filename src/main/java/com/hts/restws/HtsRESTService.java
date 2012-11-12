package com.hts.restws;

import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hts.entity.BroadcastStream;
import com.hts.exceptions.AppException;
import com.hts.service.BroadcastStreamServiceImpl;
import com.hts.service.IpAddressServiceImpl;

/**
 * GET reads a resource PUT creates a new resource DELETE removes the resources. POST updates an existing resource or
 * creates a new resource.
 */

@Path("/json/broadcast")
public class HtsRESTService {
	final Logger log = Logger.getLogger(this.getClass());

	private final BroadcastStreamServiceImpl broadcastStreamServiceImpl = new BroadcastStreamServiceImpl();

	@GET
	@Path("{broadcastName}/{ipAddress}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response checkAccess(@PathParam("broadcastName") String broadcastName, @PathParam("ipAddress") String ipAddress) throws AppException {

		String result = "[WS] Checking permisions for " + ipAddress + " to broadcastStrem " + broadcastName;
		log.info(result);

		Boolean connectionAllowed = false;
		try {
			connectionAllowed = new IpAddressServiceImpl().isBroadcastStreamAllowedForIP(ipAddress, broadcastName);
		}
		catch (UnknownHostException e) {
			log.error(e.getMessage());
		}

		result = "Connection allowed: " + connectionAllowed;

		log.info(result);

		if (connectionAllowed)
			return Response.status(201).entity(result).build();
		else
			return Response.status(403).entity(result).build();
	}

	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBroadcastStream(BroadcastStream broadcastStream) throws AppException {

		String result = "[WS] Creating broadcastStream: " + broadcastStream;
		log.info(result);
		broadcastStream = broadcastStreamServiceImpl.create(broadcastStream.getName());

		return Response.status(201).entity(result).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response unregisterAllBroadcastStreams()
			throws AppException {
		String result = "[WS] Unregistering All BroadcastStreams";
		log.info(result);
		broadcastStreamServiceImpl.unregisterAllActiveBroadcastStreams();

		return Response.status(201).entity(result).build();
	}

	
	@DELETE
	@Path("{broadcastStreamName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBroadcastStream(@PathParam("broadcastStreamName") String broadcastStreamName)
			throws AppException {

		String result = "[WS] Unregistering BroadcastStream: " + broadcastStreamName;
		log.info(result);
		broadcastStreamServiceImpl.unregisterBroadcastStream(broadcastStreamName);

		return Response.status(201).entity(result).build();

	}

}
