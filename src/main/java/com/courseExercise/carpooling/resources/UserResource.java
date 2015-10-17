package com.courseExercise.carpooling.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.courseExercise.carpooling.core.User;
import com.courseExercise.carpooling.db.MyDAO;
import com.fasterxml.jackson.annotation.JsonProperty;


@Path("/user")
public class UserResource {
	
    private MyDAO userDAO;
    
    public class Authentication{
    	private boolean authenticated;
    	
    	public Authentication() {
    	}
    	
    	@JsonProperty
		public boolean isAuthenticated() {
			return authenticated;
		}

		public void setAuthenticated(boolean authenticated) {
			this.authenticated = authenticated;
		}
    	
    	
    }
    
	public UserResource(MyDAO userDAO){
		this.userDAO = userDAO;
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public User findById(){
		User user = userDAO.findUserById("zhangsan");
		return user;
	}
    
    @Path("/auth/{LOGIN_ID}/{PASSWORD}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public Authentication authLogin(@PathParam("LOGIN_ID") String id,@PathParam("PASSWORD") String password){
    	Authentication authentication = new Authentication();
    	System.out.println(id);
    	System.out.println(password);
    	try{
    		User user = userDAO.findUserById(id);
    		if( user.getPassword().equals(password) ) authentication.setAuthenticated(true);
    		else authentication.setAuthenticated(false);
    	}catch(Exception e){
    		authentication.setAuthenticated(false);
    	}
    	return authentication;
	}
    
    
    /*
    @Path("/regist")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public View registUser(@FormParam("username") String username, @FormParam("password") String password){
    	try{
    		userDAO.insertUser(username, password);
    	}catch(Exception e){
    		return new View("/views/sigin.mustache");
    	}
		return new User(username, password);
	}
	*/
}
