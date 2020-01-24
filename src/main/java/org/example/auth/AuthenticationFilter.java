package org.example.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.example.AppConfiguration;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Secured
@Provider
@Priority(value = Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    public static final String AUTHENTICATION_SCHEME = "Bearer ";
    private Algorithm algorithm;
    private JWTVerifier verifier;

    public AuthenticationFilter(AppConfiguration config) {
        this.algorithm = Algorithm.HMAC256(config.getSecrets().getJwtSecret());
        this.verifier = JWT.require(algorithm).withIssuer("De_omgeving").build();
    }

    @Override
    public void filter(ContainerRequestContext context) {

        if (isPreflightRequest(context)) {
            abortSuccess(context);
            return;
        }

        String authorizationHeader = context.getHeaderString(HttpHeaders.AUTHORIZATION);
        System.out.println(authorizationHeader);
        if (!isValidHeader(authorizationHeader)) {
            abortUnauthorized(context);
            return;
        }

        String jwtToken = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            verifier.verify(jwtToken);
        } catch (JWTVerificationException exception) {
            abortUnauthorized(context);
        }

    }

    private void abortUnauthorized(ContainerRequestContext context) {
        this.abortByRequest(context, Response.status(Response.Status.UNAUTHORIZED).build());
    }

    private void abortSuccess(ContainerRequestContext context) {
        this.abortByRequest(context, Response.ok().build());
    }

    private void abortByRequest(ContainerRequestContext context, Response response) {
        context.abortWith(response);
    }

    /**
     * https://stackoverflow.com/questions/28065963/how-to-handle-cors-using-jax-rs-with-jersey
     * A preflight request is an OPTIONS request
     * with an Origin header.
     */
    private static boolean isPreflightRequest(ContainerRequestContext request) {
        return request.getHeaderString("Origin") != null
                && request.getMethod().equalsIgnoreCase("OPTIONS");
    }

    private boolean isValidHeader(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader
                .toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase());
    }
}
