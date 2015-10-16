package com.example.helloworld.api;

import java.io.Serializable;

public class UserAuthorization implements Serializable{
	
	private final String userId;
	private final String userName;
	public UserAuthorization(String userId, String userName)
	{
		this.userId = userId;
		this.userName = userName;
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
}
