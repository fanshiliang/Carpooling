package com.courseExercise.carpooling.auth;


import com.courseExercise.carpooling.api.UserAuthorization;

import com.courseExercise.carpooling.db.MyDAO;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import com.google.common.base.Optional;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, UserAuthorization> {
	private MyDAO myDAO;
	
	public SimpleAuthenticator(MyDAO userDao) {this.myDAO = userDao;}
	
    @Override
    public Optional<UserAuthorization> authenticate(BasicCredentials credentials) throws AuthenticationException    
    {
    	System.out.println("enter authorization");
//   		if (myDAO.getPassword(credentials.getUsername()).equals(credentials.getPassword())) {
//              return Optional.of(new UserAuthorization(credentials.getUsername(), credentials.getPassword()));
//        }
        return Optional.absent();
    }
}