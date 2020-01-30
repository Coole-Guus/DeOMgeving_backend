package org.example.resource;

import org.example.model.User;
import org.example.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"Admin"})
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }
    
    @GET
    @Path("/usersByRole/{role}")
    @RolesAllowed({"Admin", "Medewerker", "Gebruiker"})
    public List<User> getUsersByRole(@PathParam("role") String role) {
        return userService.getUsersByRole(role);
    }

    @GET
    @Path("/getAllUsers")
    @RolesAllowed({"Admin"})
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PUT
    @Path("/")
    @RolesAllowed({"Admin"})
    public Response updateUser(User user) {
        return userService.updateUser(user);
    }

    @DELETE
    @Path("/remove/{id}")
    @RolesAllowed({"Admin"})
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
