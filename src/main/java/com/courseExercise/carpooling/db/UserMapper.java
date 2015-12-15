package com.courseExercise.carpooling.db;



import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.courseExercise.carpooling.core.User;

public class UserMapper implements ResultSetMapper<User>
{
  public User map(int index, ResultSet r, StatementContext ctx) throws SQLException
  {
	  User user = new User(r.getString("id"));
	  user.setPassword(r.getString("password"));
	  user.setUserName(r.getString("username"));
	  user.setAge(r.getString("age"));
	  user.setDrivingYears(r.getString("DrivingYears"));
	  user.setGender(r.getString("gender"));
	  user.setCarOwner(r.getString("carOwner"));
	  user.setCellphone(r.getString("cellphone"));
	  return user;
  }
}
