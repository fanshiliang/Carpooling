package com.courseExercise.carpooling.auth;

import com.courseExercice.carpooling.api.UserAuthorization;

public interface AuthService<T> {
	
	public T authenticate(String userId, String password);
	public T verifyAuthentication(T token);
	public UserAuthorization getAuthorization(T token);
}
