package com.example.helloworld.resources;

import io.dropwizard.views.View;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.helloworld.api.LoginRequest;
import com.example.helloworld.api.UserAuthorization;
import com.example.helloworld.auth.AuthService;
import com.example.helloworld.views.LoginView;

@Path("/")
public class HomeResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeResource.class);
	
	private final AuthService<?> authService;
	
	@Inject
	public HomeResource(AuthService<?> authService){
		this.authService = authService;
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public  Response getView(  UserAuthorization authorization){
		String redirectUrl = "/signin";
		if(authorization != null){
			redirectUrl ="/navigation";
		}
		return Response.temporaryRedirect(URI.create(redirectUrl)).build();		
	}
	
	@GET
	@Path("signin")
	@Produces(MediaType.TEXT_HTML)
	public View getLoginView(){
		return new LoginView();
	}
}
