package org.example.service;

import org.example.model.LoginCredentials;
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

    public User onLogin(LoginCredentials credentials) {
        User user = userDAO.findUserByLoginCredentials(credentials);
        System.out.println(Response.ok().entity(user).build());
        return user;
    }
}
