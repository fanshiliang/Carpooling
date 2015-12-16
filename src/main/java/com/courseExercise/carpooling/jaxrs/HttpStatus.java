package com.courseExercise.carpooling.jaxrs;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class HttpStatus { 
	
	//401 - unauthorized
	public static Response UNAUTHORIZED = get(Status.UNAUTHORIZED,"Credentials are required to access this resource.");
	
	public static Response FORBIDDEN = get(Status.FORBIDDEN, "No permission to access this resource.");
	
	public static Response NOT_FOUND = get(Status.NOT_FOUND, "The required resource is not found.");
	
	public static Response INTERNAL_SERVER_ERROR = get(Status.INTERNAL_SERVER_ERROR, "Internal Server Error.");
	
	public static Response BAD_GATEWAY = get(Status.BAD_GATEWAY, "Bad Gateway");
	
	private static Response get(Status status, String message){
		return Response.status(status).entity(message).type(MediaType.TEXT_PLAIN_TYPE).build();
	}
}
