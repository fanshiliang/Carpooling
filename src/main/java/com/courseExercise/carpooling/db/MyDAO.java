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

	// insert user
	@SqlUpdate("insert into user (id, password) values (:id, :password)")
	void insertUser(@Bind("id") String id, @Bind("password") String password);

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
	@SqlUpdate("insert into temp_orders values (:orderNum, :carType, :seatTotal, :seatAvailable, :date, :time, :starting, :ending, :route)")
	void insertTempOrder(@Bind("orderNum") int orderNum,
			@Bind("carType") String carType, @Bind("seatTotal") int SeatToal,
			@Bind("seatAvailable") int SeatAvailable, @Bind("date") Date date,
			@Bind("time") Time time, @Bind("starting") String starting,
			@Bind("ending") String string, @Bind("route") String route);

	// find all available orders
	@SqlQuery("SELECT * FROM temp_orders WHERE (date > curdate() or (date = curdate() and time > curtime())) and seatAvailable < seatTotal")
	@Mapper(OrderMapper.class)
	List<Order> findAllOders();
	
	// find order by orderNumber
	@SqlQuery("SELECT * FROM temp_orders WHERE orderNum = :orderNum")
	@Mapper(OrderMapper.class)
	Order findOrderById(@Bind("orderNum") int orderNum);

	// find available orders by ending
	@SqlQuery("SELECT * FROM temp_orders t WHERE ( t.date > curdate() or (t.date = curdate() and t.time > curtime()) ) and seatAvailable > 0 and t.ending = :ending and t.orderNum not in (select orderNum from user_orders where id = :id)")
	@Mapper(OrderMapper.class)
	Order findOrderByEnding(@Bind("ending") String ending, @Bind("id") String id);
	

	//join order
	@SqlUpdate("insert into user_orders (id, orderNum) values (:id, :orderNum)")
	void insertUserOrder(@Bind("id") String id, @Bind("orderNum") int orderNum);
	
	//subtract available seats when user join an order
	@SqlUpdate("update temp_orders set seatAvailable = seatAvailable - 1 where orderNum = :orderNum")
	void subTractSeatsAvaible(@Bind("orderNum") int orderNum);
	
	//cancle order
	@SqlUpdate("delete from user_orders where id = :id and orderNum = :orderNum")
	void cancleOrder(@Bind("id") String id, @Bind("orderNum") int orderNum);
	
	
	//
	@SqlQuery("select * from temp_orders where orderNum in (select orderNum from user_orders where id = :id)")
	List<Order> findAllUserOrders(@Bind("id") String id);

	// find max order number
	@SqlQuery("select MAX(orderNum) from temp_orders")
	int findMaxOrderNum();
	
}
