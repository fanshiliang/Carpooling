package com.courseExercise.carpooling.filter;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.Cookie;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import org.glassfish.jersey.server.ContainerRequest;

import com.courseExercise.carpooling.api.CookieToken;
import com.courseExercise.carpooling.api.LoginToken;
import com.courseExercise.carpooling.auth.AuthService;
import com.courseExercise.carpooling.auth.CookieAuthenticator;

import io.dropwizard.util.Generics;

public class SecurityFilter<T extends CookieToken> implements ContainerRequestFilter { 
   	
	private final AuthService<T> authService;
	private final Class<T> tokenType;
	
	public SecurityFilter(AuthService<T> authService){
		this.authService = authService;
		this.tokenType = Generics.getTypeParameter(getClass(), CookieToken.class);
	}
	
	@Override
	public void  filter(ContainerRequestContext requestContext) throws IOException {
		T token = createToken(requestContext.getCookies().values().toArray(new Cookie[0]));
		if(token != null && (token = authService.verifyAuthentication(token))!= null){
		}
		else
		{
			requestContext.setRequestUri(URI.create("/signin"));
		}
	}
	
	private T createToken(Cookie[] cookies) {
		T token = null;
		try{
			if(cookies != null) {
				token = tokenType.getConstructor(Cookie[].class).newInstance((Object)cookies);				
			}
		} catch(Exception e){
		}
		return token;
	}
}