package org.example.resource;

import org.example.auth.Secured;
import org.example.model.ExperimentStats;
import org.example.service.ExperimentStatsService;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Singleton
@Path("/experimentenStats")
@Produces(MediaType.APPLICATION_JSON)
@Secured
public class ExperimentStatsResource {

    private final ExperimentStatsService service;

    public ExperimentStatsResource(ExperimentStatsService service) {
        this.service = service;
    }

    @GET
    @Path("/")
    public List<ExperimentStats> retrieveStats() {
        return service.getStats();
    }

}


