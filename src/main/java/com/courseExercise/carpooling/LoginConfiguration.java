package com.courseExercise.carpooling;



import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginConfiguration {

	@JsonProperty("server") 
	private ServerConfiguration loginServer;
	
	@NotBlank
	private String LoginUriTemplate;
	
	@NotBlank
	@JsonProperty("appName")
	private String applicationName;

	public ServerConfiguration getLoginServer() {
		return loginServer;
	}

	public void setLoginServer(ServerConfiguration loginServer) {
		this.loginServer = loginServer;
	}

	public String getLoginUriTemplate() {
		return LoginUriTemplate;
	}

	public void setLoginUriTemplate(String loginUriTemplate) {
		LoginUriTemplate = loginUriTemplate;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	
}
