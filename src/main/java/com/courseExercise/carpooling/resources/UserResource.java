package com.courseExercise.carpooling.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import com.courseExercise.carpooling.core.User;
import com.courseExercise.carpooling.db.MyDAO;
import com.fasterxml.jackson.annotation.JsonProperty;

@Path("/user")
public class UserResource {

	private MyDAO myDAO;

	public class Authentication {
		private boolean authenticated;
		private User user;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

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

	public UserResource(MyDAO myDAO) { 
		this.myDAO = myDAO;
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User findById(@PathParam("id") String id) {
		User user = myDAO.findUserById(id);
		return user;
	}
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> findAllUsers() {
		List<User> user = myDAO.findAllUsers();
		return user;
	}

	@Path("/auth/{LOGIN_ID}/{PASSWORD}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Authentication authLogin(@PathParam("LOGIN_ID") String id,
			@PathParam("PASSWORD") String password) {
		Authentication authentication = new Authentication();
		try {
			User user = myDAO.findUserById(id);
			if (user.getPassword().equals(password)){
				authentication.setAuthenticated(true);
				authentication.setUser(user);
			}		
			else
				authentication.setAuthenticated(false);
		} catch (Exception e) {
			authentication.setAuthenticated(false);
		}
		return authentication;
	}

	public class RegisterResult {
		private boolean registered;
		private User user;

		public boolean isRegistered() {
			return registered;
		}

		public void setRegistered(boolean registered) {
			this.registered = registered;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public RegisterResult() {

		}

	}

	@Path("/register")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public RegisterResult registUser(@FormParam("id") String id,
			@FormParam("password") String password,
			@FormParam("username") String userName,
			@FormParam("age") String age,
			@FormParam("carOwner") String carOwner,
			@FormParam("drivingYears") String drivingYears,
			@FormParam("gender") String gender,
			@FormParam("cellphone") String cellphone) {
		RegisterResult registerResult = new RegisterResult();
		try {
			myDAO.insertUser(id, userName, password, age, carOwner,
					drivingYears, gender, cellphone);
			User user = new User(id);
			user.setPassword(password);
			user.setUserName(userName);
			user.setCarOwner(carOwner);
			user.setDrivingYears(drivingYears);
			user.setAge(age);
			user.setGender(gender);
			user.setCellphone(cellphone);
			registerResult.setRegistered(true);
			registerResult.setUser(user);
			return registerResult;
		} catch (Exception e) {
			registerResult.setRegistered(false);
			return registerResult;
		}
	}

}
