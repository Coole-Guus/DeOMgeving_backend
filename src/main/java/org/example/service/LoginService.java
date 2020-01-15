package org.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.example.model.LoginCredentials;
import org.example.model.User;
import org.example.persistence.UserDAO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

public class LoginService {

    public static final String HS256_SECRET = "bzi-f0RGqvPXCp2qx4UqFJCmRP1ubZ0P_OLpT8BC6Lg";

    private UserDAO userDAO;

    @Inject
    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
        System.out.println("called" + userDAO);
    }

    public Response onLogin(LoginCredentials credentials) {
        User user = userDAO.findUserByLoginCredentials(credentials);

        if (user == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        String token = buildJWTToken(user);
        return JWTResponse(token);
    }

    private Response JWTResponse(String token) {
        HashMap<String, String> ResponseEntity = new HashMap<>();
        ResponseEntity.put("jwtToken", token);
        return Response.ok().entity(ResponseEntity).build();
    }


    private String buildJWTToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(HS256_SECRET);
        Date expireDate = createExpireDate(15);
        return JWT.create()
                .withIssuer("De_omgeving")
                .withIssuedAt(new Date())
                .withClaim("name", user.getName())
                .withClaim("role", user.getRole())
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    private Date createExpireDate(int minutes) {
        long MINUTES_TO_SECONDS = 60 * minutes;
        Instant instant = Instant.now().plusSeconds(MINUTES_TO_SECONDS);
        return Date.from(instant);
    }

}
