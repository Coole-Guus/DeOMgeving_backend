package org.example.resource;

import org.example.model.LoginCredentials;
import org.example.model.User;
import org.example.service.LoginService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

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
    public User onLogin(@NotNull @Valid LoginCredentials credentials) {
        System.out.println("called"  +  service);
        System.out.println("1" + credentials);
        return service.onLogin(credentials);
    }

}
