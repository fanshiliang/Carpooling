package com.example.helloworld.resources;


import com.example.helloworld.views.LoginView;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/signin")
@Produces(MediaType.TEXT_HTML)
public class SignIn {
	public SignIn(){
		
	}
	
	@GET
	public LoginView loginTest()
	{
		return new LoginView();
	}
}
