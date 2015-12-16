package com.courseExercise.carpooling.auth;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.courseExercise.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.api.CookieToken;
import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.util.Generics;

public abstract class CookieAuthenticator<T extends CookieToken> implements Authenticator<ContextCredentials, UserAuthorization> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CookieAuthenticator.class);
	
	private final AuthService<T> authService;
	private final Class<T> tokenType;
	
	protected CookieAuthenticator(AuthService<T> authService){
		this.authService = authService;
		this.tokenType = Generics.getTypeParameter(getClass(), CookieToken.class);
	}
	
	@Override
	public Optional<UserAuthorization> authenticate(ContextCredentials credentials) throws AuthenticationException {
		HttpServletRequest request = credentials.getRequest();
		T token = createToken(request.getCookies());
		if(token != null && (token = authService.verifyAuthentication(token))!=null){
			if(token.isRenewed()){
				for(Cookie cookie : token.getCookies())
					credentials.getResponse().addCookie(cookie);
			}
			UserAuthorization authorization = authService.getAuthorization(token);
			if(authorization != null ){
				return Optional.of(authorization);
			}
			return Optional.absent();
		} else if(credentials.isRequired()) {
			return Optional.absent();
		}
		return Optional.absent();
	}
	
	private String getRequestPath(ExtendedUriInfo uriInfo){
		ResourceMethod resourceMethod = uriInfo.getMatchedResourceMethod();
		UriBuilder pathBuilder = UriBuilder.fromPath("/");
		Resource parent = resourceMethod.getParent();
		List<String> resourceList = new ArrayList<>();
		while(parent != null){
			resourceList.add(parent.getPath());
			parent = parent.getParent();
		}
		for (int i = resourceList.size()-1; i >=0; i--){
			pathBuilder.path(resourceList.get(i));
		}
		String requestedPath = resourceMethod.getHttpMethod() + " " + pathBuilder.toString();
		return requestedPath;
	}
	private T createToken(Cookie[] cookies) {
		T token = null;
		try{
			if(cookies != null) {
				token = tokenType.getConstructor(Cookie[].class).newInstance((Object)cookies);				
			}
		} catch(Exception e){
			LOGGER.error("Invalid token type extending CookieToken", e);
		}
		return token;
	}
}
