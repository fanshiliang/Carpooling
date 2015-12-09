package com.courseExercise.carpooling.resources;

import com.courseExercise.carpooling.views.TCP;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tempCarPooling")
@Produces(MediaType.TEXT_HTML)
public class TempCarPooling {
	public TempCarPooling(){
		
	}
	
	@GET
	public TCP loginTest()
	{
		return new TCP();
	}
}
