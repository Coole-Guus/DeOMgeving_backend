package org.example.resource;

import org.example.model.Experiment;
import org.example.model.User;
import org.example.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @GET
    @Path("/usersByRole/{role}")
    public List<User> getUsersByRole(@PathParam("role") String role) {
        return userService.getUsersByRole(role);
    }

    @POST
    @Path("/updateUser")
    public Response updateUser(User user) {
        return userService.updateUser(user);
    }


//    @POST
//    @Path("/reset/{token}/{newPassword}")
//    public Response resetPassword(@PathParam("email") String email) {
//        this.userService.forgotPassword(email);
//        return Response.ok().build();
//    }
}
