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
	  User user = new User(r.getString("id"), r.getString("password"));
	  user.setAge(r.getInt("age"));
	  user.setDrivingYears(r.getInt("DrivingYears"));
	  user.setGender(r.getString("gender"));
	  user.setCarOwner(r.getInt("carOwner"));
	  user.setCellphone(r.getString("cellphone"));
	  return user;
  }
}
