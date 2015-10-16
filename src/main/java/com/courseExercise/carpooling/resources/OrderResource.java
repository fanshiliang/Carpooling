package com.courseExercise.carpooling.resources;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.courseExercise.carpooling.core.Order;
import com.courseExercise.carpooling.db.MyDAO;

@Path("/order")
public class OrderResource {
    private final AtomicLong counter;
    private MyDAO myDAO;
    
	public OrderResource(MyDAO myDAO){
		this.myDAO = myDAO;
		this.counter = new AtomicLong();
	}
	
	@Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Order> findAllOrders(){
		return myDAO.findAllOders();
	}
}
