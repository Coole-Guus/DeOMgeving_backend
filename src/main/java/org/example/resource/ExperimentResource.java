package org.example.resource;

import org.example.model.Experiment;
import org.example.service.ExperimentService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Stefan, Bart, Leander
 */
@Singleton
@Path("/experimenten")
@Produces(MediaType.APPLICATION_JSON)
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

    @GET
    @Path("searchDienst/{term}")
    public List<Experiment> getExperimentSearchDiesnt(@PathParam("term") String term) {
        return service.searchDienst(term);
    }


}
