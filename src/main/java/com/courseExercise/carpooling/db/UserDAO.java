
package com.courseExercise.carpooling.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import com.courseExercise.carpooling.core.User;


@RegisterMapper(UserMapper.class)
public interface UserDAO {
	
	@SqlUpdate("insert into user (id, password) values (:id, :password)")
	void insert(@Bind("id") String id, @Bind("password") String password);

	@SqlQuery("select password from user where id = :id")
	String findPasswordById(@Bind("id") String id);
	
	@SqlQuery("select id, password from user where id = :id")
	User findById(@Bind("id") String id);
	
	@SqlQuery("select * from user")
	List<User> findAll();
	
}
