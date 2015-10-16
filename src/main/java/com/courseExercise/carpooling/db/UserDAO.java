package com.courseExercise.carpooling.db;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
public interface UserDAO {
	
	@SqlUpdate("insert into user (id, password) values (:id, :password)")
	void insert(@Bind("id") String id, @Bind("password") String password);

	@SqlQuery("select password from user where id = :id")
	String findPasswordById(@Bind("id") String id);
	
}
