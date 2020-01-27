package org.example.service;

import org.example.model.UploadedFile;
import org.example.persistence.ExperimentDetailsDAO;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public class UploadService {
    private ExperimentDetailsDAO experimentDetailsDAO;

    @Inject
    public UploadService(ExperimentDetailsDAO experimentDetailsDAO) {
        this.experimentDetailsDAO = experimentDetailsDAO;
    }

    public void uploadAttachment(UploadedFile file) {
        experimentDetailsDAO.setAttachment(file);
    }

    public UploadedFile getAttachment(int experimentId) {
        return this.experimentDetailsDAO.getAttachment(experimentId);
    }
}
