package com.courseExercise.carpooling.auth;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.courseExercise.carpooling.LoginConfiguration;
import com.courseExercise.carpooling.ServerConfiguration;
import com.courseExercise.carpooling.api.DBResponse;
import com.courseExercise.carpooling.api.LoginToken;
import com.courseExercise.carpooling.exceptions.ApplicationException;
import com.google.common.base.Optional;
import com.google.common.net.HttpHeaders;
public class ClientServiceImpl {
	
	private final Client client;
	private UriBuilder loginUri;
	
	public ClientServiceImpl(Client client, LoginConfiguration loginConfig){
		this.client = client;
		ServerConfiguration c = loginConfig.getLoginServer();
		this.loginUri = UriBuilder.fromUri(loginConfig.getLoginUriTemplate()).scheme(c.getSchema()).host(c.getHost()).port(c.getPort());
	}
	
	public LoginToken authenticateUser(String userId, String password){
		UriBuilder uriBuilder = loginUri.clone().replaceQueryParam("LOGIN_ID", userId).replaceQueryParam("PASSWORD", password);
		DBResponse response = DBRequest(uriBuilder);
		if(response.getAuthCred() != null){
			return LoginToken.newToken(response);
		}
		else
			throw ApplicationException.UNAUTHORIZED;
	}
	
	private DBResponse DBRequest(UriBuilder uriBuilder){
		DBResponse oResponse = null;
		try{
			oResponse = client.target(uriBuilder).request().header(HttpHeaders.CACHE_CONTROL, "no-cache").accept(MediaType.APPLICATION_JSON).buildGet().invoke(DBResponse.class);
		} catch(ProcessingException | WebApplicationException e){
			throw ApplicationException.BAD_GATEWAY;
		}
		return oResponse;
	}
}
