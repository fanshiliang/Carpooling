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
    return new User(r.getString("id"), r.getString("password"));
  }
}
