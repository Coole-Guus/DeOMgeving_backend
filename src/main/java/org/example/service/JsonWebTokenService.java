package org.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.example.AppConfiguration;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class JsonWebTokenService {
    private String jwtSecret;
    private JWTVerifier verifier;
    private JWTVerifier verifierWithLeeway;
    private Algorithm algorithm;

    @Inject
    public JsonWebTokenService(AppConfiguration config) {
        this.jwtSecret = config.getSecrets().getJwtSecret();
        this.algorithm = Algorithm.HMAC256(jwtSecret);
        this.verifier = JWT.require(algorithm).withIssuer("De_omgeving").build();

        int leeway = config.getSecrets().getJwtRefreshLeeway();
        this.verifierWithLeeway = JWT.require(algorithm).withIssuer("De_omgeving").acceptLeeway(leeway).build();
    }

    public boolean isValid(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public boolean isValidWithLeeway(String token) {
        try {
            verifierWithLeeway.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public DecodedJWT decodeJwt(String token) {
        return verifier.verify(token);
    }

    public DecodedJWT decodeJwtWithLeeway(String token) {
        return verifierWithLeeway.verify(token);
    }

    private JWTVerifier getVerifier(int leeway) {
        return JWT.require(algorithm).withIssuer("De_omgeving")
                .acceptLeeway(leeway).build();
    }

    public String createJWTFromInvalidJWT(String JWTString) {
        DecodedJWT decodedJWT = decodeJwtWithLeeway(JWTString);
        Date expireDate = createExpireDate(15);
        return JWT.create()
                .withIssuer(decodedJWT.getIssuer())
                .withIssuedAt(decodedJWT.getIssuedAt())
                .withClaim("name", decodedJWT.getClaim("name").asString())
                .withClaim("role", decodedJWT.getClaim("role").asString())
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    private static Date createExpireDate(int minutes) {
        long MINUTES_TO_SECONDS = 60 * minutes;
        Instant instant = Instant.now().plusSeconds(MINUTES_TO_SECONDS);
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
