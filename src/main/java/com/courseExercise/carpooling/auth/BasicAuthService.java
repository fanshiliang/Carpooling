package com.courseExercise.carpooling.auth;

import com.courseExercise.carpooling.api.LoginRequest;
import com.courseExercise.carpooling.api.LoginResult;
import com.courseExercise.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.db.MyDAO;
import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;

public class BasicAuthService {
	MyDAO myDao;
	
	public BasicAuthService(MyDAO myDao){
		this.myDao = myDao;
	}
	 public Optional<LoginResult> authenticate(LoginRequest request) throws AuthenticationException    
	 {
	    	
	   		if(myDao.getPassword(request.getUserId()).equals(request.getPassword())){
	   			return Optional.of(new LoginResult(true,request.getUserId()));
	   		}
	   		else
	   			return Optional.of(new LoginResult(false,null));
     }
}
