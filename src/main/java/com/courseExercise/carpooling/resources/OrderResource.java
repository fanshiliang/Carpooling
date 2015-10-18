package com.courseExercise.carpooling.resources;

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
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Order raiseOrder(@FormParam("carType") String carType,
			@FormParam("date") Date date, @FormParam("time") String time,
			@FormParam("totalSeats") String seatTotal,
			@FormParam("availableSeats") String seatAvailable,
			@FormParam("route") String route,
			@FormParam("starting") String starting,
			@FormParam("ending") String ending) {

		newOrderNum++;
		int totalSeats = Integer.parseInt(seatTotal);
		int availableSeats = Integer.parseInt(seatAvailable);
		myDAO.insertTempOrder(newOrderNum, carType, totalSeats, availableSeats,
				date, Time.valueOf(time), starting, ending, route);
		return myDAO.findOrderById(newOrderNum);
	}

	@Path("/myOrders/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> mylOrder(@PathParam("id") String id){
		return myDAO.findAllUserOrders(id);		
	}
	
	@Path("/join/{id}/{orderNum}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Order joinOrder(@PathParam("id") String id,
			@PathParam("orderNum") int orderNum) {
		myDAO.insertUserOrder(id, orderNum);
		myDAO.addSeatsAvaible(orderNum);;
		return myDAO.findOrderById(orderNum);
	}
	
	@Path("/cancle/{id}/{orderNum}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> cancelOrder(@PathParam("id") String id, @PathParam("orderNum") int orderNum){
		myDAO.cancleOrder(id, orderNum);
		myDAO.subTractSeatsAvaible(orderNum);
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
