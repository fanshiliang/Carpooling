package com.courseExercise.carpooling.views;

import java.util.List;

import com.courseExercise.carpooling.core.Order;

import io.dropwizard.views.View;

public class OrderView extends View{
	private Order order;
	private List<Order> orderList;
	

	public OrderView(String template) {
		super(template);
	}
	
	public void setOrder(Order order){
		this.order = order;
	}
	
	public void setOrderList(List<Order> orderList){
		this.orderList = orderList;
	}
	
	public Order getOrder(){
		return order;
	}
	
	public List<Order> getOrderList(){
		return orderList;
	}
	
	

}
