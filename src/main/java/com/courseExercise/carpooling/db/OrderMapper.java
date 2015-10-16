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
		return order;
	}

}
