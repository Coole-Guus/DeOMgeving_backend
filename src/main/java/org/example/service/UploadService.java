package org.example.service;

import org.example.model.UploadedFile;
import org.example.persistence.DAOFactory;
import org.example.persistence.ExperimentDetailsDAO;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public class UploadService {
    private ExperimentDetailsDAO experimentDetailsDAO;

    @Inject
    public UploadService(DAOFactory factory) {
        this.experimentDetailsDAO = factory.onDemand(ExperimentDetailsDAO.class);
    }

    public void uploadAttachment(UploadedFile file) {
        experimentDetailsDAO.setAttachment(file);
    }

    public UploadedFile getAttachment(int experimentId) {
        return this.experimentDetailsDAO.getAttachment(experimentId);
    }
}
