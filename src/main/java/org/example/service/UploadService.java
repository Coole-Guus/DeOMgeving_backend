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
        experimentDetailsDAO.close();
    }

    public UploadedFile getAttachment(int experimentId) {
        UploadedFile uploadedFile = this.experimentDetailsDAO.getAttachment(experimentId);
        this.experimentDetailsDAO.close();
        return uploadedFile;
    }
}
