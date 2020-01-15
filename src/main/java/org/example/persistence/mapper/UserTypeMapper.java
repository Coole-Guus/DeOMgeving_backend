package org.example.persistence.mapper;

import org.example.model.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserTypeMapper implements ResultSetMapper<User> {

    @Override
    public User map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        User u = new User();
        readUserData(u, rs);
        return u;

        /*
               return new User(rs.getLong("id"), rs.getString("email"),
                rs.getString("password"), rs.getString("name"),
                rs.getString("role"), rs.getTimestamp("create_date"),
                rs.getString("password_reset_token"));
         */
    }

    private void readUserData(User u, ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int count = metaData.getColumnCount();
        for (int j = 1; j <= count; j++) {

            switch (metaData.getColumnName(j)){
                case "id":
                    u.setId(rs.getInt(j));
                    break;
                case "name":
                    u.setName(rs.getString(j));
                    break;
                case "email":
                    u.setEmail(rs.getString(j));
                    break;
                case "password":
                    u.setPassword(rs.getString(j));
                    break;
                case "createDate":
                    //"DIT STUKJE CODE IS NOG NIET GEIMPLEMENTEERD WANT IK WAS TE LUI"
                    throw new NotImplementedException();
                case "role":
                    u.setRole(rs.getString(j));
                    break;
            }
        }
    }
}
