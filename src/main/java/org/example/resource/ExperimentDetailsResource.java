package org.example.resource;

import org.example.model.ExperimentDetails;
import org.example.service.ExperimentDetailsService;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/experimentDetails")
@Produces(MediaType.APPLICATION_JSON)
public class ExperimentDetailsResource {

    private final ExperimentDetailsService service;

    @Inject
    public ExperimentDetailsResource(ExperimentDetailsService service) {
        this.service = service;
    }

    @GET
    @Path("/{id}")
    public ExperimentDetails retrieve(@PathParam("id") int id)
    {
        return service.find(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id)
    {
        service.delete(id);
    }

    @POST
    @Path("/")
    public int insert(ExperimentDetails experimentDetails)
    {
        return service.add(experimentDetails);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") int id, @Valid @NotNull ExperimentDetails experimentDetails)
    {
        service.update(id, experimentDetails);
    }
}
