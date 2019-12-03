package org.example.service;

import org.example.model.User;
import org.example.persistence.UserDAO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class LoginService {

    private UserDAO userDAO;

    @Inject
    public LoginService(UserDAO userDAO){
        this.userDAO = userDAO;
        System.out.println("called" + userDAO);
    }

    public Response onLogin(String email, String password) {
        User user = userDAO.findUserByLoginCredentials(email, password);

        return Response.ok().entity(user).build();
    }
}
