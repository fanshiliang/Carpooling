package com.courseExercise.carpooling.resources;

import io.dropwizard.views.View;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.courseExercise.carpooling.core.Order;
import com.courseExercise.carpooling.db.MyDAO;
import com.google.common.base.Charsets;

@Path("/order")
public class OrderResource {
	private MyDAO myDAO;
	private int newOrderNum;

	public OrderResource(MyDAO myDAO) {
		this.myDAO = myDAO;
		try{
			newOrderNum = myDAO.findMaxOrderNum();
		}catch(Exception e){
			newOrderNum = 0;
		}
		
		System.out.println(newOrderNum);
	}

	@Path("/all")
	@GET
	@Produces("application/json")
	public List<Order> findAllOrders() {
		return myDAO.findAllOders();
	}
	
	@Path("/availableOrders/{id}/{orderType}")
	@GET
	@Produces("application/json")
	public List<Order> findAvailableTempOrders(@PathParam("id") String id, @PathParam("orderType") String orderType) {
		return myDAO.findAvailableOders(id, orderType);
	}

	@Path("/raiseOrder")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public View raiseOrder(@FormParam("carType") String carType,
			@FormParam("orderType") String orderType,
			@FormParam("startDate") Date startDate, @FormParam("endDate") Date endDate, @FormParam("time") String time, 
			@FormParam("seatTotal") String seatTotal,
			@FormParam("seatAvailable") String seatAvailable,
			@FormParam("route") String route,
			@FormParam("starting") String starting,
			@FormParam("ending") String ending, @FormParam("id") String id) {

		newOrderNum++;
		String status = "ongoing";
		myDAO.insertTempOrder(newOrderNum, orderType, carType, Integer.parseInt(seatTotal), Integer.parseInt(seatAvailable),
				startDate, endDate, Time.valueOf(time), starting, ending, route, status);
		myDAO.insertUserOrder(id, newOrderNum);
		return new View("/views/tempCarpooling/myTempOrders.mustache", Charsets.UTF_8) {
		};

	}

	@Path("/myOrders/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> mylOrder(@PathParam("id") String id){
		myDAO.updateOrderStatus();
		return myDAO.findAllUserOrders(id);		
	}
	
	private boolean compareTime(Time t1, Time t2){
		if(Math.abs(t1.getHours()*60 + t1.getMinutes() - t2.getHours()*60 - t2.getMinutes()) > 29)
			return true;
		else return false;
	}
	
	@Path("/join/{id}/{orderNum}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public boolean joinOrder(@PathParam("id") String id,
			@PathParam("orderNum") int orderNum) {
		Order newOrder = myDAO.findOrderById(orderNum);
		List<Order> onGoing = myDAO.findUserOngoingOrder(id);
		for(Order order: onGoing){
			if(!compareTime(order.getTime(), newOrder.getTime())){
				return false;
			}				
		}
		myDAO.insertUserOrder(id, orderNum);
		myDAO.subTractSeatsAvaible(orderNum);
		return true;
	}
	
	@Path("/cancle/{id}/{orderNum}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> cancelOrder(@PathParam("id") String id, @PathParam("orderNum") int orderNum){
		myDAO.cancleOrder(id, orderNum);
		myDAO.addSeatsAvaible(orderNum);
		return myDAO.findAllUserOrders(id);		
	}

	public class OrderList {
		private List<Order> orderList;
		private List<SiteList> siteList;

		public class SiteList {
			private String site;
			private List<Integer> orderNumList;

			public String getSite() {
				return site;
			}

			public List<Integer> getOrderNumList() {
				return orderNumList;
			}

			public void setOrderNumList(List<Integer> orderNumList) {
				this.orderNumList = orderNumList;
			}

			public void setSite(String site) {
				this.site = site;
			}

			public SiteList() {

			}
		}

		public OrderList() {

		}

		public List<Order> getOrderList() {
			return orderList;
		}

		public void setOrderList(List<Order> orderList) {
			this.orderList = orderList;
		}

		public List<SiteList> getSiteList() {
			return siteList;
		}

		public void setSiteList() {
			List<SiteList> allSiteList = new ArrayList<SiteList>();
			List<String> sites = new ArrayList<String>();
			for (Order order : orderList) {
				String[] orderSites = order.getRouteList();
				for (int i = 0; i < order.getSiteCount(); i++) {
					if (sites.contains(orderSites[i]))
						continue;
					else
						sites.add(orderSites[i]);
				}
			}
			List<Integer> orderNumList;
			for (String site : sites) {
				orderNumList = new ArrayList<Integer>();
				for (Order order : orderList) {
					String[] orderSites = order.getRouteList();
					for (int i = 0; i < order.getSiteCount(); i++) {
						if (orderSites[i].equals(site)) {
							orderNumList.add(order.getOrderNum());
						}
					}
				}
				SiteList siteList = new SiteList();
				siteList.setSite(site);
				siteList.setOrderNumList(orderNumList);
				allSiteList.add(siteList);
			}
			this.siteList = allSiteList;

		}
	}

	@Path("/search/{dst}")
	@GET
	@Produces("application/json")
	public OrderList searchOrder(@PathParam("dst") String dst) {
		OrderList orderList = new OrderList();
		orderList.setOrderList(myDAO.findOrderByEndingNoID(dst));
		orderList.setSiteList();
		return orderList;
	}

}
