package com.courseExercise.carpooling.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.courseExercise.carpooling.core.Order;

public class OrderMapper implements ResultSetMapper<Order> {

	@Override
	public Order map(int arg0, ResultSet r, StatementContext arg2)
			throws SQLException {
		Order order = new Order(r.getInt("orderNum"));
		order.setCarType(r.getString("carType"));
		order.setOrderType(r.getString("orderType"));
		order.setSeatTotal(r.getInt("seatTotal"));
		order.setSeatAvailable(r.getInt("seatAvailable"));
		order.setStartDate(r.getDate("startDate"));
		order.setEndDate(r.getDate("endDate"));
		order.setTime(r.getTime("time"));
		order.setStarting(r.getString("starting"));
		order.setEnding(r.getString("ending"));
		order.setRoute(r.getString("route"));
		order.setStatus(r.getString("status"));
		return order;
	}

}
