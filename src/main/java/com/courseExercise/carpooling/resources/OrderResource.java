package com.courseExercise.carpooling.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.courseExercise.carpooling.core.Order;
import com.courseExercise.carpooling.db.MyDAO;
import com.courseExercise.carpooling.views.RaiseOrderView;

@Path("/order")
public class OrderResource {
    private MyDAO myDAO;
    
	public OrderResource(MyDAO myDAO){
		this.myDAO = myDAO;
	}
	
	@Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Order> findAllOrders(){
		return myDAO.findAllOders();
	}
	
	@Path("/raise")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public RaiseOrderView raiseOrder()
	{
		return new RaiseOrderView();
	}
}
