package com.courseExercise.carpooling.resources;

import io.dropwizard.views.View;

import com.courseExercise.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.views.NavigationView;
import com.courseExercise.carpooling.views.TempCarpoolingView;
import com.google.common.base.Charsets;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Carpooling")
public class CarPoolingViewResource { 
	public CarPoolingViewResource() {

	}

//	@Produces(MediaType.TEXT_HTML)
//	@GET
//	@Path("/tempCarpooling")
//	public View getTempIndexView() {
//		return new View("/views/tempCarpooling/tempCarPooling.mustache", Charsets.UTF_8) {
//		};
//	}
	
	@GET
	@Path("/tempCarpooling")
	@Produces(MediaType.TEXT_HTML)
	public View getTempCarpoolingView(){
		return new TempCarpoolingView(null);
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/raiseTempOrder")
	public View getRaiseTempOrderView() {
		return new View("/views/tempCarpooling/raiseTempOrder.mustache", Charsets.UTF_8) {
		};
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/longTerm")
	public View getRaiseLongOrderView() {
		return new View("/views/tempCarpooling/raiseTempOrder.mustache", Charsets.UTF_8) {
		};
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/JoinTemporaryCarpooling")
	public View getAvailableTempOrdersView() {
		return new View("/views/tempCarpooling/tempOrders.mustache", Charsets.UTF_8) {
		};
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/JoinLongtermCarpooling")
	public View getAvailableLongtermOrdersView() {
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
