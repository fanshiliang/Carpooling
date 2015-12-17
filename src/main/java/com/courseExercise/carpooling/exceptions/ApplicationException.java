package com.courseExercise.carpooling.exceptions;

import javax.ws.rs.core.Response;

import com.courseExercise.carpooling.jaxrs.HttpStatus;

public class ApplicationException extends RuntimeException{ 
	
	public static ApplicationException NOT_FOUND = new ApplicationException(HttpStatus.NOT_FOUND);
	
	public static ApplicationException UNAUTHORIZED = new ApplicationException(HttpStatus.UNAUTHORIZED);
	
	public static ApplicationException FORBIDDEN = new ApplicationException(HttpStatus.FORBIDDEN);
	
	public static ApplicationException INTERNAL_SERVER_ERROR = INTERNAL_SERVER_ERROR(null);
	public static ApplicationException INTERNAL_SERVER_ERROR(Throwable cause) {
		return new ApplicationException(cause, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public static ApplicationException BAD_GATEWAY = BAD_GATEWAY(null);
	public static ApplicationException BAD_GATEWAY(Throwable cause) {
		return new ApplicationException(cause, HttpStatus.BAD_GATEWAY);
	}
	
	private Response response;
	
	public ApplicationException(){
		this(null,null);
	}
	
	public ApplicationException(Throwable cause) {
		this(cause, null);
	}
	
	public ApplicationException(Response response){
		this(null, response);
	}
	
	public ApplicationException(Throwable cause, Response response){
		super(cause);
		if(response == null){
			this.response = Response.serverError().build();
		}
		else
			this.response = response;
	}
	
	public Response getResponse(){
		return response;
	}
	
}
