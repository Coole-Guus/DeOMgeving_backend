package org.example.persistence.mapper;

import org.example.model.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeMapper implements ResultSetMapper<User> {

    @Override
    public User map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new User(rs.getLong("id"), rs.getString("email"),
                rs.getString("password"), rs.getString("name"),
                rs.getString("role"), rs.getTimestamp("create_date"),
                rs.getString("password_reset_token"));
    }
}
