package org.example.auth;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import org.example.model.JWTUser;
import org.example.service.JsonWebTokenService;

import javax.inject.Inject;
import java.util.Optional;

public class JWTAuthenticator implements Authenticator<String, JWTUser> {
    private JsonWebTokenService service;

    @Inject
    public JWTAuthenticator(JsonWebTokenService service) {
        this.service = service;
    }

    @Override
    public Optional<JWTUser> authenticate(String jwtToken) throws AuthenticationException {
        JWTUser user = null;
        if (tokenIsValid(jwtToken)) {

            DecodedJWT decodedJWT = service.decodeJwt(jwtToken);
            Claim role = decodedJWT.getClaim("role");
            Claim name = decodedJWT.getClaim("name");

            if (role.isNull() || name.isNull()) {
                return failAuth();
            }

            user = new JWTUser();
            user.setRole(role.asString());
            user.setName(name.asString());

            return Optional.of(user);
        }

        return failAuth();
    }

    private boolean tokenIsValid(String token) {
        return service.isValid(token);
    }

    private Optional<JWTUser> failAuth() {
        return Optional.empty();
    }
}


