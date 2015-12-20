package com.courseExercise.carpooling.resources;

import io.dropwizard.views.View;

import com.courseExercise.carpooling.api.UserAuthorization;
import com.courseExercise.carpooling.views.LongTermCarpoolingView;
import com.courseExercise.carpooling.views.LongTermOrdersView;
import com.courseExercise.carpooling.views.MyOrdersView;
import com.courseExercise.carpooling.views.NavigationView;
import com.courseExercise.carpooling.views.RaiseLongOrderView;
import com.courseExercise.carpooling.views.RaiseTempOrderView;
import com.courseExercise.carpooling.views.TempCarpoolingView;
import com.courseExercise.carpooling.views.TempOrdersView;
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
	@Path("/longTerm")
	@Produces(MediaType.TEXT_HTML)
	public View getLongTermCarpoolingView(){
		return new LongTermCarpoolingView(null);
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/raiseTempOrder")
	public View getRaiseTempOrderView() {
		return new RaiseTempOrderView(null) {
		};
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/raiseLongOrder")
	public View getRaiseLongOrderView() {
		return new RaiseLongOrderView(null) {
		};
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/viewTempOrders")
	public View getTempOrdersView() {
		return new TempOrdersView(null) {
		};
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/viewLongTermOrders")
	public View getLongTermOrdersView() {
		return new LongTermOrdersView(null) {
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
		return new  MyOrdersView(null);
		};
	
	

	
	
	

	
}
