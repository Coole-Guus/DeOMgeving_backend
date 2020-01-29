package org.example.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.AppConfiguration;
import org.example.model.Credentials;
import org.example.model.LoginCredentials;
import org.example.model.RegisterCredentials;
import org.example.model.User;
import org.example.persistence.DAOFactory;
import org.example.persistence.UserDAO;
import org.example.util.CryptographicUtils;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class AuthService {

    private JsonWebTokenService jwtService;

    private final String passwordHashKey;
    private final String jwtSecret;
    private UserDAO userDAO;
    private AppConfiguration config;

    @Inject
    public AuthService(DAOFactory factory, AppConfiguration config, JsonWebTokenService jwtService) {
        this.jwtService = jwtService;
        this.userDAO = factory.onDemand(UserDAO.class);
        this.passwordHashKey = config.getSecrets().getPasswordHash();
        this.jwtSecret = config.getSecrets().getJwtSecret();
    }

    public Response createUser(RegisterCredentials credentials) {
        Optional<User> optionalUser = getUserByEmail(credentials);

        if(optionalUser.isPresent())
            return Response.status(Response.Status.CONFLICT).build();

        String salt = CryptographicUtils.generateSalt();
        userDAO.create(credentials, salt, passwordHashKey);

        return Response.ok().build();
    }

    public Response onLogin(LoginCredentials credentials) {
        Optional<User> optionalUser = getUserByEmail(credentials);

        if (!optionalUser.isPresent() ) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        User user = optionalUser.get();

        String salt = user.getSalt();

        if (user.getRole().equalsIgnoreCase ("UNIDENTIFIED") || !this.checkUserPassword(credentials, salt)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String token = CryptographicUtils.buildJWTToken(user, jwtSecret);
        return JWTResponse(token);
    }

    private boolean checkUserPassword(Credentials credentials, String salt) {
        return userDAO.userExistWithCredentials(credentials, salt, passwordHashKey);
    }

    private Optional<User> getUserByEmail(Credentials credentials) {
        User user = userDAO.findUserByEmail(credentials.getEmail());
        return Optional.ofNullable(user);
    }

    private Response JWTResponse(String token) {
        HashMap<String, String> ResponseEntity = new HashMap<>();
        ResponseEntity.put("jwtToken", token);
        return Response.ok().entity(ResponseEntity).build();
    }


    public Response refreshToken(HttpHeaders headers) {
        System.out.println();
        if (!jwtService.hasJWTHeader(headers)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        String oldJWT = headers.getRequestHeader("Authorization").get(0);
        oldJWT = oldJWT.replace("Bearer ", "");

        if(!jwtService.isValid(oldJWT, 60 * 10)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String newJWT = jwtService.createJWTFromInvalidJWT(oldJWT);

        return JWTResponse(newJWT);
    }

}
