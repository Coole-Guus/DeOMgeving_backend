package org.example.service;

import org.example.persistence.UserDAO;
import org.example.util.CryptographicUtils;
import ru.vyarus.dropwizard.guice.module.context.stat.Stat;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class UserService {

    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void forgotPassword(String email) {
        String password_reset_token = String.valueOf(CryptographicUtils.generateFourDigitNumber());
        userDAO.setToken(email, password_reset_token);
    }

    public Response delete(int id) {
        if (this.userDAO.removeUser(id) == 1)
            return Response.ok().build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
