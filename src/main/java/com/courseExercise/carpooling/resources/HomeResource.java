package com.courseExercise.carpooling.resources;

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

import com.courseExercice.carpooling.api.LoginRequest;
import com.courseExercice.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.auth.AuthService;
import com.courseExercise.carpooling.views.LoginView;
import com.google.common.base.Charsets;

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
	
	@GET
	@Path("navigation")
	@Produces(MediaType.TEXT_HTML)
	public View getNavigationView(){
		return new View("/views/navigation.mustache", Charsets.UTF_8) {
		};
	}
}
