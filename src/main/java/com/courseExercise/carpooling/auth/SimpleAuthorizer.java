package com.courseExercise.carpooling.auth;

import com.courseExercise.carpooling.api.UserAuthorization;

import io.dropwizard.auth.Authorizer;

public class SimpleAuthorizer implements Authorizer<UserAuthorization> {

    @Override
    public boolean authorize(UserAuthorization userAuthorization, String role) {
        if (role.equals("ADMIN") && !userAuthorization.getName().startsWith("fan")) {
            return true;
        }
        return false;
    }
}