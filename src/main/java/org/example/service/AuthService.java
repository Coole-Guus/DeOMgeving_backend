package org.example.service;

import org.example.AppConfiguration;
import org.example.model.LoginCredentials;
import org.example.model.RegisterCredentials;
import org.example.model.User;
import org.example.persistence.UserDAO;
import org.example.util.CryptographicUtils;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Optional;

public class AuthService {

    private final String passwordHashKey;
    private final String jwtSecret;
    private UserDAO userDAO;
    private AppConfiguration config;

    @Inject
    public AuthService(UserDAO userDAO, AppConfiguration config) {
        this.userDAO = userDAO;
        this.passwordHashKey = config.getSecrets().getPasswordHash();
        this.jwtSecret = config.getSecrets().getJwtSecret();
    }

    public Response create(RegisterCredentials user) {
        String salt = CryptographicUtils.generateSalt();

        userDAO.create(user, salt, passwordHashKey);
        return Response.ok().build();
    }

    public Response onLogin(LoginCredentials credentials) {
        Optional<User> optionalUser = getUserByEmail(credentials);

        if (!optionalUser.isPresent()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        User user = optionalUser.get();

        String salt = user.getSalt();

        if (!this.checkUserPassword(credentials, salt)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String token = CryptographicUtils.buildJWTToken(user, jwtSecret);
        return JWTResponse(token);
    }

    private boolean checkUserPassword(LoginCredentials credentials, String salt) {
        return userDAO.userExistWithCredentials(credentials, salt, passwordHashKey);
    }

    private Optional<User> getUserByEmail(LoginCredentials credentials) {
        return Optional.ofNullable(userDAO.findUserByEmail(credentials.getEmail()));
    }

    private Response JWTResponse(String token) {
        HashMap<String, String> ResponseEntity = new HashMap<>();
        ResponseEntity.put("jwtToken", token);
        return Response.ok().entity(ResponseEntity).build();
    }


}
