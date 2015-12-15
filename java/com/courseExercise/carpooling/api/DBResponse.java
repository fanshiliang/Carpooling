package com.courseExercise.carpooling.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DBResponse {
	
	private AuthCredentials authCred;
	
	//private String error;
	
	//@JsonProperty("session_key_expires_after")
	//private String sessionKeyExpiration;

	public AuthCredentials getAuthCred() {
		return authCred;
	}
	
	public void setAuthCred(AuthCredentials authCred) {
		this.authCred = authCred;
	}

	/*public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSessionKeyExpiration() {
		return sessionKeyExpiration;
	}

	public void setSessionKeyExpiration(String sessionKeyExpiration) {
		this.sessionKeyExpiration = sessionKeyExpiration;
	}*/

	
}
