package org.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.AppConfiguration;
import org.example.model.User;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class JsonWebTokenService {
    private final String ISSUER;
    private final Algorithm ALGORITHM;
    private final int EXPIRE_TIME_IN_SECONDS;
    private final JWTVerifier VERIFIER;
    private final JWTVerifier VERIFIER_WITH_LEEWAY;


    @Inject
    public JsonWebTokenService(AppConfiguration config) {
        String SECRET = config.getJwtConfig().getSecret();
        this.ISSUER = config.getJwtConfig().getIssuer();
        this.ALGORITHM = Algorithm.HMAC256(SECRET);
        this.VERIFIER = JWT.require(ALGORITHM).withIssuer(ISSUER).build();
        this.EXPIRE_TIME_IN_SECONDS = config.getJwtConfig().getExpireDate();

        int leeway = config.getJwtConfig().getJwtRefreshLeeway();
        this.VERIFIER_WITH_LEEWAY = JWT.require(ALGORITHM).withIssuer(ISSUER).acceptLeeway(leeway).build();
    }

    public boolean isValid(String token) {
        try {
            VERIFIER.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public boolean isValidWithLeeway(String token) {
        try {
            VERIFIER_WITH_LEEWAY.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public DecodedJWT decodeJwt(String token) {
        return VERIFIER.verify(token);
    }

    public DecodedJWT decodeJwtWithLeeway(String token) {
        return VERIFIER_WITH_LEEWAY.verify(token);
    }


    public String createJwt(User user) {
        Date expireDate = createExpireDate(this.EXPIRE_TIME_IN_SECONDS);
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withClaim("name", user.getName())
                .withClaim("role", user.getRole())
                .withExpiresAt(expireDate)
                .sign(ALGORITHM);
    }

    public String createJWTFromInvalidJWT(String JWTString) {
        DecodedJWT decodedJWT = decodeJwtWithLeeway(JWTString);
        Date expireDate = createExpireDate(this.EXPIRE_TIME_IN_SECONDS);
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withClaim("name", decodedJWT.getClaim("name").asString())
                .withClaim("role", decodedJWT.getClaim("role").asString())
                .withExpiresAt(expireDate)
                .sign(ALGORITHM);
    }

    private static Date createExpireDate(int seconds) {
        Instant instant = Instant.now().plusSeconds(seconds);
        return Date.from(instant);
    }

    public boolean hasJWTHeader(HttpHeaders headers) {
        List<String> header = headers.getRequestHeader("Authorization");

        if (header == null || header.size() < 1) {
            return false;
        }

        String JWT = header.get(0);
        if (!JWT.contains("Bearer ")) {
            return false;
        }

        return true;
    }

}
