package com.courseExercise.carpooling.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResult {
	private boolean result;
	
	public LoginResult(boolean result) {
		this.result = result;
	}
	
	@JsonProperty
	public boolean getResult(){
		return result;
	}
}
