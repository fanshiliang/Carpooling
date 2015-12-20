package com.courseExercise.carpooling.auth;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.courseExercise.carpooling.LoginConfiguration;
import com.courseExercise.carpooling.ServerConfiguration;
import com.courseExercise.carpooling.api.DBResponse;
import com.courseExercise.carpooling.api.LoginToken;
import com.courseExercise.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.exceptions.ApplicationException;
import com.courseExercise.carpooling.db.MyDAO;
import com.google.common.base.Optional;
import com.google.common.net.HttpHeaders;
public class ClientServiceImpl {
	
	private MyDAO myDao;
	
	public ClientServiceImpl(MyDAO myDao){
		this.myDao = myDao;
	}
	
	public LoginToken authenticateUser(String userId, String password){
		if (this.myDao.getPassword(userId).equals(password)) {
			return LoginToken.newToken(userId);
		}
		else
			throw ApplicationException.UNAUTHORIZED;
	}
	
}
