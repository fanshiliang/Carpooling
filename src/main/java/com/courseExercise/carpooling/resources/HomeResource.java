package com.courseExercise.carpooling.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.views.View;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import com.google.common.base.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.courseExercise.carpooling.api.LoginRequest;
import com.courseExercise.carpooling.api.LoginResult;
import com.courseExercise.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.CarpoolingApplication;
import com.courseExercise.carpooling.api.CookieToken;
import com.courseExercise.carpooling.auth.AuthService;
import com.courseExercise.carpooling.auth.AuthServiceImpl;
import com.courseExercise.carpooling.auth.BasicAuthService;
import com.courseExercise.carpooling.auth.SimpleAuthenticator;
import com.courseExercise.carpooling.views.LoginView;
import com.courseExercise.carpooling.views.NavigationView;
import com.courseExercise.carpooling.views.TestNavigationView;

@Path("/")
public class HomeResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeResource.class);
	
	AuthServiceImpl authService;
	
	@Inject
	public HomeResource(AuthServiceImpl authService){
		this.authService = authService;
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public  Response getView(UserAuthorization authorization){
		String redirectUrl = "/signin";
		if(authorization != null){
			redirectUrl ="/navigation";
		}
		return Response.temporaryRedirect(URI.create(redirectUrl)).build();		
	}
	
	@GET
	@Path("navigation")
	@Produces(MediaType.TEXT_HTML)
	public View getNavigationView( UserAuthorization authorization){
		return new NavigationView(null);
	}
	
	
	@GET
	@Path("signin")
	@Produces(MediaType.TEXT_HTML)
	public View getLoginView(){
		return new LoginView();
	}
		
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest loginRequest){
		return Response.ok().cookie(getCookies((CookieToken) authService.authenticate(loginRequest.getUserId(), loginRequest.getPassword()),false)).build();
	}
	
	@POST
	@Path("logout")
	@Produces(MediaType.TEXT_HTML)
	public Response logout(@Auth UserAuthorization authorization){
		return Response.ok().cookie(getCookies(authorization.getToken(),true)).build();
	}
	
	private NewCookie[] getCookies(CookieToken token, boolean delete){
		List<NewCookie> newCookies = new ArrayList<>();
		for(Cookie cookie : token.getCookies()){
			newCookies.add(
					new NewCookie(cookie.getName(),
							cookie.getValue(),
							cookie.getPath(),
							cookie.getDomain(),
							cookie.getComment(),
							delete ? 0 : cookie.getMaxAge(),
							cookie.getSecure())
					);
		}
		return newCookies.toArray(new NewCookie[] {});
	}
}
