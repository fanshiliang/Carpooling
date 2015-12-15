package com.courseExercise.carpooling.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthCredentials {

		private boolean authenticated;
		
		@JsonProperty("userId")
		private String userId;
		
		@JsonProperty("userName")
		private String userName;
		
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public boolean isAuthenticated() {
			return authenticated;
		}
		
		public void setAuthenticated(Boolean authenticated){
			this.authenticated = authenticated;
		}
		

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}
		
	
}
