package org.example.resource;

import org.example.model.LoginCredentials;
import org.example.model.RegisterCredentials;
import org.example.service.AuthService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class AuthResource {

    private AuthService service;

    @Inject
    public AuthResource(AuthService service) {
        this.service = service;
    }

    @POST
    @Path("login")
    public Response onLogin(@NotNull @Valid LoginCredentials loginCredentials) {
        return service.onLogin(loginCredentials);
    }

    @GET
    @Path("refresh")
    public Response refreshToken(@Context HttpHeaders headers) {
        return service.refreshToken(headers);
    }

    @POST
    @Path("register")
    public Response registerUser(@NotNull @Valid RegisterCredentials registerCredentials) {
        return this.service.createUser(registerCredentials);
    }
}
