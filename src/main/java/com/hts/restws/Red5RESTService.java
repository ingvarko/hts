package com.hts.restws;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.red5.demos.oflaDemo.DemoService;

import com.hts.exceptions.AppException;

/**
 * GET reads a resource PUT creates a new resource DELETE removes the resources. POST updates an existing resource or
 * creates a new resource.
 */

@Path("/json/red5")
public class Red5RESTService {
	final Logger log = Logger.getLogger(this.getClass());

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getgetListOfAvailableFLVs() throws AppException {

		String result = "[Red5-WS] Getting all flvs";
		log.info(result);

		Map<String, Map<String, Object>> m = new DemoService().getListOfAvailableFLVs();
		
		JSONObject json = new JSONObject();
		json.accumulateAll(m);

		result =  json.toString();

		return Response.status(201).entity(result).build();
	}

}
