package com.courseExercise.carpooling.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResult {
	private boolean result;
	private String username;
	public LoginResult(boolean result,String username) {
		this.result = result;
		this.username = username;
	}
	
	@JsonProperty
	public boolean getResult(){
		return result;
	}
	@JsonProperty
	public String getUsername(){
		return username;
	}
}
