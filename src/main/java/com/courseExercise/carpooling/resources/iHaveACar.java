package com.courseExercise.carpooling.resources;

import com.courseExercise.carpooling.views.IHAC;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/iHaveACar")
@Produces(MediaType.TEXT_HTML)
public class iHaveACar {
	public iHaveACar(){
		
	}
	
	@GET
	public IHAC loginTest()
	{
		return new IHAC();
	}
}
