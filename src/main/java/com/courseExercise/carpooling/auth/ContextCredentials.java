package com.courseExercise.carpooling.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.glassfish.jersey.server.ExtendedUriInfo;

public class ContextCredentials {
 
	private final boolean required;
	private final ExtendedUriInfo uriInfo;
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	 
	public ContextCredentials(boolean required, ExtendedUriInfo uriInfo, HttpServletRequest request, HttpServletResponse response){
		this.required = required;
		this.uriInfo = uriInfo;
		this.request = request;
		this.response = response;
	}
	
	public boolean isRequired() {
		return required;
	}
	
	public ExtendedUriInfo getUriInfo() {
		return uriInfo;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
}
