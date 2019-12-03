package org.example.persistence;

import org.example.model.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface UserDAO {

    @SqlQuery("SELECT * FROM user WHERE name = :name AND password = :password")
    public User findUserWithPassword(@Bind("email") String name, @Bind("password") String password);

    @SqlUpdate("INSERT INTO user (email, password, name, rol, create_date, token) " +
            "VALUES (:email, :password, :name, :role, :create_date, :token)")
    public void create(@BindBean User newUser);

    @SqlUpdate("UPDATE user SET token = :token WHERE id = :id")
    public void setToken(@Bind("id") int id, @Bind("token") String token);

}
