package org.example.auth;

import com.auth0.jwt.JWT;
import io.dropwizard.auth.Authorizer;
import org.example.model.JWTUser;

import javax.annotation.Nullable;
import javax.ws.rs.container.ContainerRequestContext;

public class JWTAuthorizer implements Authorizer<JWTUser> {

    /** @deprecated */
    @Override
    public boolean authorize(JWTUser jwtUser, String s) {
        return false;
    }

    @Override
    public boolean authorize(JWTUser principal, String role, @Nullable ContainerRequestContext context) {
       return principal.getRole().equalsIgnoreCase(role);
    }
}
