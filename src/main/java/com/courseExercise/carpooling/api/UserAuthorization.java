package com.courseExercise.carpooling.api;

import java.security.Principal;
import javax.security.auth.Subject;


public class UserAuthorization implements Principal {
	
	private LoginToken token;
	private final String username;

	public UserAuthorization(LoginToken token, String username)
	{
		this.username = username;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public LoginToken getToken() {
		return token;
	}

	public boolean implies(Subject arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
