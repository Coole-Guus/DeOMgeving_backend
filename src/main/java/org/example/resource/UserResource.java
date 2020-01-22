package org.example.resource;

import org.example.auth.Secured;
import org.example.model.Experiment;
import org.example.model.User;
import org.example.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Secured
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

    @GET
    @Path("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PUT
    @Path("/")
    public Response updateUser(User user) {
        return userService.updateUser(user);
    }

    @DELETE
    @Path("/remove/{id}")
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
