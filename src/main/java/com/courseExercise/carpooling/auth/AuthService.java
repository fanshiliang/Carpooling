package com.courseExercise.carpooling.auth;

import com.courseExercise.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.api.LoginToken;

public interface AuthService<T> {
	
	public T authenticate(String userId, String password);
	public T verifyAuthentication(T token);
	public UserAuthorization getAuthorization(T token);
}