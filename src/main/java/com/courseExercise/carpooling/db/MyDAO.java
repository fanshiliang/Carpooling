package com.courseExercise.carpooling.db;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.courseExercise.carpooling.core.Order;
import com.courseExercise.carpooling.core.User;

public interface MyDAO {
	
	//query password
	@SqlUpdate("SELECT password From user where id = :id")
	String getPassword(@Bind("id") String id);
	
	// insert user
	@SqlUpdate("insert into user values (:id, :username, :password, :age, :carOwner, :drivingYears, :gender, :cellphone)")
	void insertUser(@Bind("id") String id, @Bind("username") String username,
			@Bind("password") String password, @Bind("age") String age,
			@Bind("carOwner") String carOwner,
			@Bind("drivingYears") String drivingYears,
			@Bind("gender") String gender, @Bind("cellphone") String cellphone);

	// find user's password by id
	@SqlQuery("select password from user where id = :id")
	String findPasswordById(@Bind("id") String id);

	// find user by id
	@SqlQuery("select * from user where id = :id")
	@Mapper(UserMapper.class)
	User findUserById(@Bind("id") String id);

	// find All users
	@SqlQuery("select * from user")
	@Mapper(UserMapper.class)
	List<User> findAllUsers();

	// insert temp_order
	@SqlUpdate("insert into orders values (:orderNum, :orderType, :carType, :seatTotal, :seatAvailable, :startDate, :endDate, :time, :starting, :ending, :route, :status)")
	void insertTempOrder(@Bind("orderNum") int orderNum,
			@Bind("orderType") String orderType, @Bind("carType") String carType,
			@Bind("seatTotal") int seatToal,
			@Bind("seatAvailable") int seatAvailable, @Bind("startDate") Date startDate, @Bind("endDate") Date endDate,
			@Bind("time") Time time, @Bind("starting") String starting,
			@Bind("ending") String ending, @Bind("route") String route, @Bind("status") String status);

	// find all available orders
	@SqlQuery("SELECT * FROM orders WHERE  ( ( startDate > curdate() or (startDate = curdate() and time > curtime())) or (orderType='Long' and endDate > curdate()) )and seatAvailable > 0")
	@Mapper(OrderMapper.class)
	List<Order> findAllOders();
	
	//find all available orders to User
	@SqlQuery("SELECT * FROM orders WHERE orderType = :orderType and ( ( startDate > curdate() or (startDate = curdate() and time > curtime())) or (orderType='Long' and endDate > curdate()) )and seatAvailable > 0 and orderNum NOT IN (SELECT orderNum FROM user_orders WHERE id = 'jingshihao')")
	@Mapper(OrderMapper.class)
	List<Order> findAvailableOders(@Bind("id") String id, @Bind("orderType") String orderType);
	
	

	// find order by orderNumber
	@SqlQuery("SELECT * FROM orders WHERE orderNum = :orderNum")
	@Mapper(OrderMapper.class)
	Order findOrderById(@Bind("orderNum") int orderNum);

	// find available orders by ending
	@SqlQuery("SELECT * FROM orders t WHERE ( t.date > curdate() or (t.date = curdate() and t.time > curtime()) ) and seatAvailable > 0 and t.ending = :ending and t.orderNum not in (select orderNum from user_orders where id = :id)")
	@Mapper(OrderMapper.class)
	Order findOrderByEnding(@Bind("ending") String ending, @Bind("id") String id);

	// find available orders by ending
	@SqlQuery("SELECT * FROM orders t WHERE ( t.date > curdate() or (t.date = curdate() and t.time > curtime()) ) and seatAvailable > 0 and t.ending = :ending")
	@Mapper(OrderMapper.class)
	List<Order> findOrderByEndingNoID(@Bind("ending") String ending);

	// join order
	@SqlUpdate("insert into user_orders (id, orderNum) values (:id, :orderNum)")
	void insertUserOrder(@Bind("id") String id, @Bind("orderNum") int orderNum);

	// subtract available seats when user join an order
	@SqlUpdate("update orders set seatAvailable = seatAvailable - 1 where orderNum = :orderNum")
	void subTractSeatsAvaible(@Bind("orderNum") int orderNum);

	// cancle order
	@SqlUpdate("delete from user_orders where id = :id and orderNum = :orderNum")
	void cancleOrder(@Bind("id") String id, @Bind("orderNum") int orderNum);

	// add available seats when user cancle an order
	@SqlUpdate("update orders set seatAvailable = seatAvailable - 1 where orderNum = :orderNum")
	void addSeatsAvaible(@Bind("orderNum") int orderNum);

	//
	@SqlQuery("select * from orders where orderNum in (select orderNum from user_orders where id = :id)")
	@Mapper(OrderMapper.class)
	List<Order> findAllUserOrders(@Bind("id") String id);

	// find max order number
	@SqlQuery("select MAX(orderNum) from orders")
	int findMaxOrderNum();
	
	// find time of all ongoing orders
	@SqlQuery("SELECT * FROM orders WHERE ( ( startDate > curdate() or (startDate = curdate() and time > curtime())) or (orderType='Long' and endDate > curdate()) ) and orderNum IN (SELECT orderNum FROM user_orders WHERE id = :id)")
	@Mapper(OrderMapper.class)
	List<Order> findUserOngoingOrder(@Bind("id") String id);

}
