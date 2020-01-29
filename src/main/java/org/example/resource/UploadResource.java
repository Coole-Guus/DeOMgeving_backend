package org.example.resource;

import org.example.model.UploadedFile;
import org.example.service.UploadService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("upload")
@Produces(MediaType.APPLICATION_JSON)
public class UploadResource {
    private final UploadService uploadService;

    @Inject
    public UploadResource(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GET
    @Path("/attachment/{experimentId}")
    public UploadedFile getAttachment(@PathParam("experimentId") int experimentId) {
        return uploadService.getAttachment(experimentId);
    }

    @POST
    @Path("/attachment/{experimentId}/{name}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response uploadAttachment(String data,
             @PathParam("experimentId") int experimentId,
             @PathParam("name") String name) {

        UploadedFile file = new UploadedFile();
        file.setExperimentId(experimentId);
        file.setFileName(name);
        file.setFileData(data);

        this.uploadService.uploadAttachment(file);

        return Response.ok().build();
    }


}
