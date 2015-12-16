package com.courseExercise.carpooling.auth;

import com.courseExercise.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.core.User;
import com.courseExercise.carpooling.db.UserDAO; 

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import com.google.common.base.Optional;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, UserAuthorization> {


    @Override
    public Optional<UserAuthorization> authenticate(BasicCredentials credentials) throws AuthenticationException    
    {
    	if ("111".equals(credentials.getPassword())) {
            return Optional.of(new UserAuthorization(credentials.getUsername(), credentials.getPassword()));
        }
        return Optional.absent();
    }
}