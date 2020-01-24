package org.example.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import org.example.model.JWTUser;

import java.util.Optional;

public class JWTAuthenticator implements Authenticator<String, JWTUser> {

    /**
     * 1. kijken of het valid is.
     * 2. decodeer jwt token
     * 3. new JWTUser, username en de role
     *
     * @param jwtToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Optional<JWTUser> authenticate(String jwtToken) throws AuthenticationException {

        return Optional.empty();
    }
}


