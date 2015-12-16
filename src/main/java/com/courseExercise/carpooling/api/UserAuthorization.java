package com.courseExercise.carpooling.api;

import java.security.Principal;
import javax.security.auth.Subject;


public class UserAuthorization implements Principal {
	

	private final String username;
	private final String password;

	public UserAuthorization(String username, String password)
	{
		this.username = username;
		this.password = password;
		
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
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
