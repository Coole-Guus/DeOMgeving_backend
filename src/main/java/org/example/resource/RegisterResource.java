package org.example.resource;

import org.example.model.RegisterCredentials;
import org.example.model.User;
import org.example.service.RegisterService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Stefan Damen
 */
@Singleton
@Path("register")
@Produces(MediaType.APPLICATION_JSON)
public class RegisterResource {
    private final RegisterService registerService;

    @Inject
    public RegisterResource(RegisterService registerService) {
        this.registerService = registerService;
    }

    @POST
    public Response registerUser(@Valid RegisterCredentials registerCredentials) {
        return this.registerService.create(registerCredentials);
    }
}
