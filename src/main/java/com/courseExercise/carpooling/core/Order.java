package com.courseExercise.carpooling.core;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
	private int orderNum;
	private String carType;
	private int seatTotal;
	private int seatAvailable;
	private Time time;
	private Date date;
	private String starting;
	private String ending;
	private String route;
	
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
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
	}
	
	
}
