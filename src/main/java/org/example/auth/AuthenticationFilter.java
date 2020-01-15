package org.example.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.service.LoginService;
import org.example.service.UserService;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Secured
@Provider
@Priority(value = Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    public static final String AUTHENTICATION_SCHEME = "Bearer ";
    Logger logger = Logger.getLogger(AuthenticationFilter.class.getSimpleName());

    Algorithm algorithm = Algorithm.HMAC256(LoginService.HS256_SECRET);
    JWTVerifier verifier = JWT.require(algorithm).withIssuer("De_omgeving").build();

    @Override
    public void filter(ContainerRequestContext context) throws IOException {

        if (isPreflightRequest(context)) {
            context.abortWith(Response.ok().entity("Im save >;").build());
            return;
        }
        String authorizationHeader = context.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!isValidHeader(authorizationHeader)) {
            abortUnauthorized(context);
            return;
        }

        String jwtToken = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
        System.out.println("\n\n\n\n\njwt " + jwtToken + "\n\n\n\n");

        try {
            verifier.verify(jwtToken);
            System.out.println("success");
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
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
