package com.courseExercise.carpooling.core;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
	private int orderNum;
	private String orderType;
	private String carType;
	private int seatTotal;
	private int seatAvailable;
	private Date startDate;
	private Date endDate;
	private Time time;
	private String starting;
	private String ending;
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String route;
	private String status;
	private String[] routeList;
	private int siteCount;
	
	public String[] getRouteList() {
		return routeList;
	}

	public void setRouteList(String[] routeList) {
		this.routeList = routeList;
	}
	
	public int getSiteCount() {
		return siteCount;
	}

	public void setSiteCount(int siteCount) {
		this.siteCount = siteCount;
	}

	public Order(){
		
	}
	
	public Order(int orderNum){
		this.orderNum = orderNum;
	}
	
	@JsonProperty
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	@JsonProperty
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	@JsonProperty
	public int getSeatTotal() {
		return seatTotal;
	}
	public void setSeatTotal(int seatTotal) {
		this.seatTotal = seatTotal;
	}
	
	@JsonProperty
	public int getSeatAvailable() {
		return seatAvailable;
	}
	public void setSeatAvailable(int seatAvailable) {
		this.seatAvailable = seatAvailable;
	}


	@JsonProperty
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	@JsonProperty
	public String getStarting() {
		return starting;
	}
	public void setStarting(String starting) {
		this.starting = starting;
	}
	
	@JsonProperty
	public String getEnding() {
		return ending;
	}
	public void setEnding(String ending) {
		this.ending = ending;
	}
	
	@JsonProperty
	public String getRoute() {
		return route;
	}
	
	public void setRoute(String route) {
		this.route = route;
		String[] routeList = route.split(",");
		this.siteCount = routeList.length + 1;
		String[] routeListAll = new String[this.siteCount];
		for(int i = 0; i < routeList.length; i ++){
			routeListAll[i] = routeList[i];
		}
		routeListAll[routeList.length] = this.starting;
		this.routeList = routeListAll;
		
	}
	
}
