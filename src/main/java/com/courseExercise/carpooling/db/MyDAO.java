package com.courseExercise.carpooling.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.courseExercise.carpooling.core.Order;
import com.courseExercise.carpooling.core.User;

public interface MyDAO {

	// insert user
	@SqlUpdate("insert into user (id, password) values (:id, :password)")
	void insertUser(@Bind("id") String id, @Bind("password") String password);

	// find user's password by id
	@SqlQuery("select password from user where id = :id")
	String findPasswordById(@Bind("id") String id);

	// find user by id
	@SqlQuery("select id, password from user where id = :id")
	User findUserById(@Bind("id") String id);

	// find All users
	@SqlQuery("select * from user")
	@Mapper(UserMapper.class)
	List<User> findAllUsers();

	// insert temp_order
	@SqlUpdate("insert into temp_order (orderNum) values (:orderNum)")
	void insertTempOrder(@Bind("orderNum") int orderNum);

	// find all available orders
	@SqlQuery("SELECT * FROM temp_orders WHERE date > curdate() or (date = curdate() and time > curtime())")
	@Mapper(OrderMapper.class)
	List<Order> findAllOders();

	@SqlUpdate("insert into user_orders (id, orderNum) values (:id, :orderNum)")
	void insertUserOrder(@Bind("id") String id, @Bind("orderNum") int orderNum);

	// find available orders by ending
	@SqlQuery("SELECT * FROM temp_orders t WHERE ( t.date > curdate() or (t.date = curdate() and t.time > curtime()) ) and t.ending = :ending and t.orderNum not in (select orderNum from user_orders where id = :id)")
	Order findOrderByEnding(@Bind("ending") String ending, @Bind("id") String id);
	
	
}
