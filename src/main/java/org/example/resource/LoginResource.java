package org.example.resource;

import org.example.model.LoginCredentials;
import org.example.service.LoginService;
import org.example.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Ashna Wiar
 */
@Singleton
@Path("login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    private final LoginService service;

    @Inject
    public LoginResource(LoginService service) {
        this.service = service;
        System.out.println("called 2");
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response onLogin(@NotNull @Valid LoginCredentials credentials) {
        System.out.println("called" +  service);
        return service.onLogin(credentials.getEmail(), credentials.getPassword());
    }
}
