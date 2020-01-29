package org.example.service;

import org.example.model.ExperimentDetails;
import org.example.persistence.DAOFactory;
import org.example.persistence.ExperimentDetailsDAO;

import javax.inject.Inject;

public class ExperimentDetailsService extends BaseService<ExperimentDetails> {
    private final ExperimentDetailsDAO dao;

    @Inject
    public ExperimentDetailsService(DAOFactory factory) {
        this.dao = factory.onDemand(ExperimentDetailsDAO.class);
    }

    public ExperimentDetails find(int id) {
        ExperimentDetails result = dao.find(id);
        dao.close();
        return requireResult(result);
    }

    public int add(ExperimentDetails experimentDetails) {
        int response = dao.addExperimentDetails(experimentDetails);
        dao.close();
        return response;
    }

    public void update(int id, ExperimentDetails experiment) {
        dao.updateExperimentDetails(id, experiment);
        dao.close();;
    }

    public void delete(int id) {
        dao.deleteExperimentDetails(id);
        dao.close();
    }
}
