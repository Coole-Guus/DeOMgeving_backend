package org.example.service;

import org.example.model.RegisterCredentials;
import org.example.model.User;
import org.example.persistence.UserDAO;
import org.example.resource.RegisterResource;


import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class RegisterService {
    private UserDAO userDAO;

    @Inject
    public RegisterService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public Response create(RegisterCredentials user) {
        userDAO.create(user);
        return Response.ok().build();
    }
}
