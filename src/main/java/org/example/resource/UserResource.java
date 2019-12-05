package org.example.resource;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.service.RegisterService;
import org.example.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Properties;

@Singleton
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }


    @POST
    @Path("/forgot/{email}")
    public Response forgotPassword(@PathParam("email") String email) {
        this.userService.forgotPassword(email);
        return Response.ok().build();
    }

    @POST
    @Path("/reset/{token}/{newPassword}")
    public Response forgotPassword(@PathParam("email") String email) {
        this.userService.forgotPassword(email);
        return Response.ok().build();
    }
}
