package com.courseExercise.carpooling.auth;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.courseExercise.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.api.LoginToken;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Sets;;

public class AuthServiceImpl implements AuthService<LoginToken>{
	
	
	private final ClientServiceImpl clientService;
	
	private final Cache<String, LoginToken> authenticationCache;
	private final Cache<String, UserAuthorization> authorizationCache;
	
	public AuthServiceImpl(int cacheDuration, ClientServiceImpl clientService){
		this.clientService = clientService;
		this.authenticationCache = createCache(cacheDuration, LoginToken.class);
		this.authorizationCache = createCache(cacheDuration, UserAuthorization.class);
	}
	
	private<T> Cache<String,T> createCache(int cacheDuration, Class<T> valueType){
		Cache<String, T> cache = null;
		if(cacheDuration <= 0){
			cache =  CacheBuilder.newBuilder().build();
		} else {
			cache =  CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, TimeUnit.SECONDS).build();
		}
		return cache;
	}
	
	@Override
	public LoginToken authenticate(String userId, String password){
		final LoginToken token = clientService.authenticateUser(userId, password);
		authenticationCache.put(userId, token);
		return token;
	}
	
	@Override
	public UserAuthorization getAuthorization(LoginToken token) {
		String userId =  token.getUserId();
		UserAuthorization authorization = authorizationCache.getIfPresent(userId);
		if(authorization != null){
			return authorization;
		}
		authorization = new UserAuthorization(token, userId);
		
		authorizationCache.put(userId, authorization);
		return authorization;
	}
	
	@Override
	public LoginToken verifyAuthentication(LoginToken token) {
		if(token.equals(authenticationCache.getIfPresent(token.getUserId()))){
			return token;
		}
		return null;
	}
}
