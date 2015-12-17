package com.courseExercise.carpooling.views;

import org.apache.commons.lang3.StringUtils;

import com.courseExercise.carpooling.api.UserAuthorization;

public class MasterView extends PrefixedView { 
	protected final UserAuthorization authorization;
	protected final String username;
	
	protected MasterView(String templateName, UserAuthorization authorization){
		super(templateName);
		this.authorization = authorization;
		this.username = (authorization != null) ? authorization.getUsername() : StringUtils.EMPTY;
	}
	
	public String getUsername() {
		return username;
	}
}
