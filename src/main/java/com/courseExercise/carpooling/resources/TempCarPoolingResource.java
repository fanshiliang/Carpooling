package com.courseExercise.carpooling.resources;

import io.dropwizard.views.View;
import com.google.common.base.Charsets;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tempCarpooling")
public class TempCarPoolingResource {
	public TempCarPoolingResource() {

	}

	@Produces(MediaType.TEXT_HTML)
	@GET
	public View getTempIndexView() {
		return new View("/views/tempCarpooling/tempCarPooling.mustache", Charsets.UTF_8) {
		};
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/raise")
	public View getRaiseOrderView() {
		return new View("/views/tempCarpooling/raiseTempOrder.mustache", Charsets.UTF_8) {
		};
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/orders")
	public View getAllOrdersView() {
		return new View("/views/tempCarpooling/tempOrders.mustache", Charsets.UTF_8) {
		};
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/myOrders")
	public View getMyOrdersView() {
		return new View("/views/tempCarpooling/myTempOrders.mustache", Charsets.UTF_8) {
		};
	}
	
	
	
	
}
