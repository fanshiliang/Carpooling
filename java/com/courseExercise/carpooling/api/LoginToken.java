package com.courseExercise.carpooling.api;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;

import com.google.common.collect.Lists;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class LoginToken extends CookieToken{
	
	private static final String APP_USERID = "SappUserId";
	private static final String APP_USER_NAME = "SappUserName";
	private static final String APP_CHALLENGE = "SappChallenge";
	
	public static LoginToken newToken(DBResponse response) {
		AuthCredentials authCred = response.getAuthCred();
		String userId = authCred.getUserId();
		String userName = authCred.getUserName();
		String challenge = UUID.randomUUID().toString();
		int maxAge = 24*60*60;
		List<Cookie> cookies = Lists.newArrayList(
				newCookie(APP_USERID, userId, maxAge),
				newCookie(APP_USER_NAME, userName,maxAge),
				newCookie(APP_CHALLENGE, Base64.encodeBase64String(challenge.getBytes()),maxAge)				
		);
		
		return new LoginToken(cookies.toArray(new Cookie[] {}));
		
	}
	
	private static int getMaxAge(String sessionKeyExpiration){
		int hoursToExpire = NumberUtils.toInt(StringUtils.substringBefore(sessionKeyExpiration,":"));
		int minsToExpire = NumberUtils.toInt(StringUtils.substringAfter(sessionKeyExpiration,":"));
		
		return hoursToExpire*60*60 + minsToExpire*60;
	}
	
	private static Cookie newCookie(String name, String value, int maxAge){
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	private String userId;
	private String userName;
	private String challenge;
	private String hash;
	private boolean renewed = false;
	
	
	public LoginToken(Cookie[] cookies){
		super(cookies);
		for(Cookie c : cookies){
			if(StringUtils.equals(c.getName(), APP_USERID)){
				this.userId = c.getValue();
			} else if(StringUtils.equals(c.getName(), APP_USER_NAME)){
				this.userName = c.getValue();	
			} else if(StringUtils.equals(c.getName(), APP_CHALLENGE)){
				this.challenge = c.getValue();
			}
		}
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getChallenge() {
		return challenge;
	}

	public String getHash() {
		return hash;
	}
	
	@Override
	public boolean isRenewed(){
		return renewed;
	}
	
	@Override
	public boolean equals(Object obj){
		
		if(obj instanceof LoginToken){
			LoginToken token = (LoginToken) obj;
			return StringUtils.equals(this.getUserId(),token.getUserId()) &&
					StringUtils.equals(this.getUserName(),token.getUserName()) &&
					StringUtils.equals(this.getChallenge(), token.getChallenge()) &&
					StringUtils.equals(this.getHash(), token.getHash());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = userId.hashCode();
		result = 31 * result + userName.hashCode();
		result = 31 * result + challenge.hashCode();
		result = 31 * result + hash.hashCode();
		return result;
	}
}
