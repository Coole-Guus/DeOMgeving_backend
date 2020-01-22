package org.example.service;

import org.example.model.User;
import org.example.persistence.UserDAO;
import org.example.util.CryptographicUtils;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void forgotPassword(String email) {
        String password_reset_token = String.valueOf(CryptographicUtils.generateFourDigitNumber());
        userDAO.setToken(email, password_reset_token);
    }

    public List<User> getUsersByRole(String role) {
        return userDAO.getUsersByRole(role);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public Response updateUser(User user) {
        return Response.ok().entity(userDAO.updateUser(user)).build();
    }

    public Response delete(int id) {
        if (this.userDAO.removeUser(id) == 1)
            return Response.ok().build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
