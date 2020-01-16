package org.example.service;

import org.example.model.ExperimentDetails;
import org.example.persistence.ExperimentDetailsDAO;

import javax.inject.Inject;

public class ExperimentDetailsService  extends BaseService<ExperimentDetails> {
    private final ExperimentDetailsDAO dao;

    @Inject
    public ExperimentDetailsService(ExperimentDetailsDAO dao) {
        this.dao = dao;
    }

    public ExperimentDetails find(int id) {
        return requireResult(dao.find(id));
    }

    public int add(ExperimentDetails experimentDetails) {
        return dao.addExperimentDetails(experimentDetails);
    }

    public void update(int id, ExperimentDetails experiment) {
        dao.updateExperimentDetails(id, experiment);
    }

    public void delete(int id) {
        dao.deleteExperimentDetails(id);
    }
}
