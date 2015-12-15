package com.courseExercise.carpooling.api;

import javax.servlet.http.Cookie;

public abstract class CookieToken {
	protected final Cookie[] cookies;
	
	protected CookieToken(Cookie[] cookies){
		this.cookies = cookies;
	}
	
	public Cookie[] getCookies(){
		return cookies;
	}
	
	public abstract boolean isRenewed();
}
