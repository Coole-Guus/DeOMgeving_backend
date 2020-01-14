package org.example.resource;

import org.example.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @DELETE
    @Path("/{id}")
    public Response removeUser(@PathParam("id") int id) {
        return this.userService.delete(id);
    }


//    @POST
//    @Path("/reset/{token}/{newPassword}")
//    public Response resetPassword(@PathParam("email") String email) {
//        this.userService.forgotPassword(email);
//        return Response.ok().build();
//    }
}
