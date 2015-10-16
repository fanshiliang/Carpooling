package com.courseExercise.carpooling.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.courseExercise.carpooling.core.Template;
import com.courseExercise.carpooling.core.User;
import com.courseExercise.carpooling.db.UserDAO;


@Path("/user")
public class UserResource {
	
    private final AtomicLong counter;
    private final Template template;
    private UserDAO userDAO;
    
	public UserResource(UserDAO userDAO, Template template){
		this.userDAO = userDAO;
		this.counter = new AtomicLong();
		this.template = template;
	}
	

    @POST  
	public User createUser(@FormParam("id") String id, @FormParam("passWord") String passWord){
		User user = new User(id , passWord);
		System.out.println(id);
		return user;
	}
	
	
	/*
    @GET
    @Timed(name = "get-requests")
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public LoginResult getUser(@PathParam("id") String id, @PathParam("password") String password) {
    	if(password.equals(userDAO.findPasswordById(id))) return new LoginResult(true);
        return new LoginResult(false);
    }*/
}
