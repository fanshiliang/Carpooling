package com.courseExercise.carpooling.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.views.View;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.courseExercise.carpooling.api.LoginRequest;
import com.courseExercise.carpooling.api.LoginToken;
import com.courseExercise.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.api.CookieToken;
import com.courseExercise.carpooling.auth.AuthServiceImpl;
import com.courseExercise.carpooling.views.LoginView;
import com.courseExercise.carpooling.views.NavigationView;
import com.courseExercise.carpooling.views.RegisterView;


@Path("/")
public class HomeResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeResource.class);
	
	private final AuthServiceImpl authService;
	
	@Inject
	public HomeResource(AuthServiceImpl authService){
		this.authService = authService;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public  Response getView(@Context HttpServletRequest request){
		String redirectUrl;
		if(request.getCookies() != null){
			LoginToken token = new LoginToken(request.getCookies());
			
			if(token != null && (token = authService.verifyAuthentication(token))!=null){			
				redirectUrl ="/navigation";
			}else{
				redirectUrl = "/signin";
			}
		}
		else
			redirectUrl = "/signin";
		return Response.temporaryRedirect(URI.create(redirectUrl)).build();		
	}
	
	@GET
	@Path("navigation")
	@Produces(MediaType.TEXT_HTML)
	public View getNavigationView(){
		return new NavigationView(null);
	}
	
	
	@GET
	@Path("signin")
	@Produces(MediaType.TEXT_HTML)
	public View getLoginView(){
		return new LoginView();
	}
	

	@GET
	@Path("register")
	@Produces(MediaType.TEXT_HTML)
	public View getRegisterView(){
		return new RegisterView(null);
	}
		
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest loginRequest){
		return Response.ok().cookie(getCookies((CookieToken) authService.authenticate(loginRequest.getUserId(), loginRequest.getPassword()),false)).build();
	}
	
	@GET
	@Path("logout")
	@Produces(MediaType.TEXT_HTML)
	public Response logout(@Context HttpServletRequest request){
		LoginToken token = new LoginToken(request.getCookies());
		return Response.ok().cookie(getCookies(token,true)).build();
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
