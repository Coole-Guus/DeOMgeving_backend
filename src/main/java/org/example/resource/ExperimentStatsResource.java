package org.example.resource;

import org.example.auth.Secured;
import org.example.model.ExperimentStats;
import org.example.service.ExperimentStatsService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Singleton
@Path("/experimentenStats")
@Produces(MediaType.APPLICATION_JSON)
@Secured
public class ExperimentStatsResource {

    private final ExperimentStatsService service;

    @Inject
    public ExperimentStatsResource(ExperimentStatsService service) {
        this.service = service;
    }

    @GET
    @Path("/{datum}")
    public ExperimentStats retrieveStats(@PathParam("datum") String datum) {
        return service.getStats(datum);
    }

}


