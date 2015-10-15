package com.example.helloworld.auth;

import com.example.helloworld.api.UserAuthorization;

public interface AuthService<T> {
	
	public T authenticate(String userId, String password);
	public T verifyAuthentication(T token);
	public UserAuthorization getAuthorization(T token);
}
