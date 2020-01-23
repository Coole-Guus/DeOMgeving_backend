package org.example.persistence;

import org.example.model.LoginCredentials;
import org.example.model.RegisterCredentials;
import org.example.model.User;
import org.example.persistence.mapper.UserTypeMapper;
import org.example.util.CryptographicUtils;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import javax.ws.rs.core.Response;
import java.util.List;

@RegisterMapper(UserTypeMapper.class)
public interface UserDAO {

    @SqlQuery("SELECT true FROM user WHERE email = :email AND " +
            "password = SHA2( CONCAT(:password , :salt , :secret_key), " + CryptographicUtils.PASSWORD_HASH_LENGTH +")")
    public boolean userExistWithCredentials(
            @BindBean LoginCredentials login, @Bind("salt") String salt, @Bind("secret_key") String secret_key);

    @SqlQuery("Select * FROM user WHERE email = :email")
    @Mapper(UserTypeMapper.class)
    public User findUserByEmail(@Bind("email") String email);



    @SqlUpdate("INSERT INTO user (email, password, name, salt) VALUES ( :email, SHA2( CONCAT(:password, :salt , :secret_key), "+CryptographicUtils.PASSWORD_HASH_LENGTH+ "), :name, :salt)")
    public void create(@BindBean RegisterCredentials newUser,
                       @Bind("salt") String salt, @Bind("secret_key") String secret_key);



    @SqlUpdate("UPDATE user SET password_reset_token = :token WHERE email = :email")
    public void setToken(@Bind("email") String email, @Bind("token") String token);



    @SqlUpdate("UPDATE user SET password = :password WHERE token = :token")
    public void updatePassword(@Bind("password") String password, @Bind("token") String token);



    @SqlUpdate("UPDATE user SET email = :email, name = :name, role = :role WHERE id = :id")
    public int updateUser(@BindBean User user);



    @SqlQuery("SELECT id, name, email FROM user WHERE role = :role")
    @Mapper(UserTypeMapper.class)
    public List<User> getUsersByRole(@Bind("role") String role);

    @SqlQuery("SELECT id, name, email, role FROM user ORDER BY role DESC")
    @Mapper(UserTypeMapper.class)
    public List<User> getAllUsers();

    @SqlQuery("SELECT salt FROM user where email = :email")
    String getUserSalt(@Bind("email") String email);

    @SqlUpdate("DELETE FROM user WHERE id = :id")
    public int removeUser(@Bind("id") int id);

}
