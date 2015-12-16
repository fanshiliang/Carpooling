package com.courseExercise.carpooling.api;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
 
	@NotBlank(message = "Bank Id must not be blank")
	@JsonProperty("u")
	private String userId;
	
	@NotBlank(message = "password must not be blank")
	@JsonProperty("p")
	private String password;
	
	public String getUserId(){
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
