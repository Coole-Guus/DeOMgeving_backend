package org.example.service;

import org.example.model.User;
import org.example.persistence.DAOFactory;
import org.example.persistence.UserDAO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    @Inject
    public UserService(DAOFactory factory) {
        this.userDAO = factory.onDemand(UserDAO.class);
    }

    public List<User> getUsersByRole(String role) {
        List<User> userList = userDAO.getUsersByRole(role);
        userDAO.close();
        return userList;
    }

    public List<User> getAllUsers() {
        List<User> userList = userDAO.getAllUsers();
        userDAO.close();
        return userList;
    }

    public Response updateUser(User user) {
        int queryResponse = userDAO.updateUser(user);
        return updateResponse(queryResponse);
    }

    public Response delete(int id) {
        int queryResponse = userDAO.removeUser(id);
        return updateResponse(queryResponse);
    }

    private Response updateResponse(int queryResponse) {
        userDAO.close();
        int IS_CREATED = 1;
        if (queryResponse == IS_CREATED)
            return Response.ok().build();

        return Response.status(Response.Status.CONFLICT).build();
    }
}
