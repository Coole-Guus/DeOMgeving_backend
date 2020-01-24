package org.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.example.AppConfiguration;

import javax.inject.Inject;

public class JsonWebTokenService {
    private String jwtSecret;
    private JWTVerifier verifier;
    private Algorithm algorithm;

    @Inject
    public JsonWebTokenService(AppConfiguration config) {
        this.jwtSecret = config.getSecrets().getJwtSecret();
        this.algorithm = Algorithm.HMAC256(jwtSecret);
        this.verifier = JWT.require(algorithm).withIssuer("De_omgeving").build();
    }

    public boolean isValid(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public DecodedJWT decodeJwt(String token) {
        return verifier.verify(token);
    }
}
