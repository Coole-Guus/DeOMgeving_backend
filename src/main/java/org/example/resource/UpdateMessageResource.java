package org.example.resource;
import org.example.auth.Secured;
import org.example.model.Message;
import org.example.service.UpdateMessageService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Leander
 */
@Singleton
@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Secured
public class UpdateMessageResource {

    private final UpdateMessageService service;

    @Inject
    public UpdateMessageResource(UpdateMessageService service) {
        this.service = service;
    }

    @GET
    @Path("/{experimentId}")
    public List<Message> getMessages (@PathParam("experimentId") int experimentId) {
        return service.getAllMessages(experimentId);
    }

    @POST
    @Path("/{experimentId}")
    public Response insert(@Valid @NotNull Message newMessage, @PathParam("experimentId") int experimentId) {
        return service.addMessage(newMessage, experimentId);
    }


}
