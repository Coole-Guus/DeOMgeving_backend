package org.example.resource;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.View;
import org.example.auth.Secured;
import org.example.model.Experiment;
import org.example.model.ExperimentDetails;
import org.example.service.ExperimentService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author Stefan, Bart, Leander
 */
@Singleton
@Path("/experimenten")
@Produces(MediaType.APPLICATION_JSON)
@Secured
public class ExperimentResource {

    private final ExperimentService service;

    @Inject
    public ExperimentResource(ExperimentService service) {
        this.service = service;
    }

    @GET
    @Path("/")
    @RolesAllowed({"Admin", "Medewerker", "Gebruiker"})
    public List<Experiment> retrieveAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin", "Medewerker", "Gebruiker"})
    public Experiment retrieve(@PathParam("id") int id) {
        return service.find(id);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Admin", "Medewerker"})
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }

    @POST
    @Path("/")
    @RolesAllowed({"Admin", "Medewerker"})
    public int insert(Experiment experiment) {
        return service.add(experiment);
//        HashMap<String, Integer> responceEntity = new HashMap<>();
//        responceEntity.put("id", id);
//        return Response.ok().entity(responceEntity).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Admin", "Medewerker"})
    public void update(@PathParam("id") int id, Experiment experiment) {
        service.update(id, experiment);
    }



    //--------------------FILTER, ORDER, SEARCH--------------------

    @GET
    @Path("filter/{operation}/{attribute}/{value}")
    @RolesAllowed({"Admin", "Medewerker", "Gebruiker"})
    public List<Experiment> getExperimentSelection(
            @PathParam("operation") String operation,
            @PathParam("attribute") String attribute,
            @PathParam("value") String value) {
        return service.selectBy(operation, attribute, value);
    }

    @GET
    @Path("search/{term}")
    @RolesAllowed({"Admin", "Medewerker", "Gebruiker"})
    public List<Experiment> getExperimentSearch(@PathParam("term") String term) {
        return service.selectBy("search", term, "");
    }
}
