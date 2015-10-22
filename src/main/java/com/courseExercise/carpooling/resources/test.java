package com.courseExercise.carpooling.resources;

import com.courseExercise.carpooling.views.TTEESSTT;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Produces(MediaType.TEXT_HTML)
public class test {
	public test(){
		
	}
	
	@GET
	public TTEESSTT loginTest()
	{
		return new TTEESSTT();
	}
}
