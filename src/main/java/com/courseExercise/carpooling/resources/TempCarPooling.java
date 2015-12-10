package com.courseExercise.carpooling.resources;

import io.dropwizard.views.View;

import com.courseExercise.carpooling.views.RaiseOrder;
import com.courseExercise.carpooling.views.TCP;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tempCarPooling")

public class TempCarPooling {
	public TempCarPooling(){
		
	}
	
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	@GET
	public TCP loginTest()
	{
		return new TCP();
	}
	
	@Path("/raise")
	@Produces(MediaType.TEXT_HTML)
	@GET
	public RaiseOrder raiseOrder()
	{
		return new RaiseOrder();
	}
	
}
