package com.courseExercise.carpooling.resources;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.courseExercise.carpooling.core.Order;
import com.courseExercise.carpooling.db.MyDAO;

@Path("/order")
public class OrderResource {
	private MyDAO myDAO;
	private int newOrderNum;

	public OrderResource(MyDAO myDAO) {
		this.myDAO = myDAO;
		newOrderNum = myDAO.findMaxOrderNum();
		System.out.println(newOrderNum);
	}
	
	@Path("/all")
	@GET
	@Produces("application/json")
	public List<Order> findAllOrders() {
		return myDAO.findAllOders();
	}

	@Path("/raiseOrder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order raiseOrder(@FormParam("carType") String carType,
			@FormParam("seatTotal") int seatTotal,
			@FormParam("seatAvailable") int seatAvailable,
			@FormParam("date") Date date, @FormParam("time") Time time,
			@FormParam("route") String route,
			@FormParam("starting") String starting,
			@FormParam("ending") String ending) {
		
		newOrderNum ++;
		myDAO.insertTempOrder(newOrderNum, carType, seatTotal, seatAvailable, date, time, starting, ending, route);
		
		Order order = new Order();
		order.setOrderNum(newOrderNum);
		order.setSeatTotal(seatTotal);
		order.setSeatAvailable(seatAvailable);
		order.setDate(date);
		order.setTime(time);
		order.setStarting(starting);
		order.setEnding(ending);
		order.setRoute(route);
				
		return order;
	}
	
	@Path("/join")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order joinOrder(@FormParam("id") String id, @FormParam("orderNum") int orderNum){
		myDAO.insertUserOrder(id, orderNum);
		myDAO.subTractSeatsAvaible(orderNum);
		return myDAO.findOrderById(orderNum);
	}
	

}
