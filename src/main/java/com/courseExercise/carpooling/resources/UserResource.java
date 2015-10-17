package com.courseExercise.carpooling.resources;

import io.dropwizard.views.View;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.courseExercise.carpooling.core.User;
import com.courseExercise.carpooling.db.MyDAO;


@Path("/user")
public class UserResource {
	
    private MyDAO userDAO;
    
	public UserResource(MyDAO userDAO){
		this.userDAO = userDAO;
	}
	
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public User findById(){
		User user = userDAO.findUserById("zhangsan");
		return user;
	}
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<User> findAllUsers(){
		return userDAO.findAllUsers();
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
