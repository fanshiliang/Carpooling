
package com.courseExercise.carpooling.db;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.courseExercise.carpooling.core.Order;
import com.courseExercise.carpooling.core.User;


public interface MyDAO {
	
	@SqlUpdate("insert into user (id, password) values (:id, :password)")
	void insertUser(@Bind("id") String id, @Bind("password") String password);

	@SqlQuery("select password from user where id = :id")
	String findPasswordById(@Bind("id") String id);
	
	@SqlQuery("select id, password from user where id = :id")
	User findUserById(@Bind("id") String id);
	
	@SqlQuery("select * from user")
	@Mapper(UserMapper.class)
	List<User> findAllUsers();
	
	@SqlQuery("select * from temp_orders")
	@Mapper(OrderMapper.class)
	List<Order> findAllOders();
	
}
