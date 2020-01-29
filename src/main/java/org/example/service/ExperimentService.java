package org.example.service;

import javax.inject.Inject;

import org.example.model.Experiment;
import org.example.persistence.DAOFactory;
import org.example.persistence.ExperimentDAO;

import java.util.List;

public class ExperimentService extends BaseService<Experiment> {

    private final ExperimentDAO dao;

    @Inject
    public ExperimentService(DAOFactory factory) {
        this.dao = factory.onDemand(ExperimentDAO.class);
    }

    public List<Experiment> getAll() {
        List<Experiment> experimentList = dao.getAll();
        dao.close();
        return experimentList;
    }

    public Experiment find(int id) {
        Experiment experiment = requireResult(dao.find(id));
        dao.close();
        return experiment;
    }

    public int add(Experiment experiment) {
        dao.add(experiment);
        int lastId = dao.lastInsert();
        dao.addMessage(lastId);
        dao.close();
        return lastId;
    }

    public void update(int id, Experiment experiment) {
        dao.update(id, experiment);
        dao.close();
    }

    public void delete(int id) {
        dao.delete(id);
        dao.close();
    }

    public List<Experiment> selectBy(String operation, String attribute, String value) {
        List<Experiment> experimentList = null;
        switch (operation) {
            case "order":
                experimentList = dao.orderBy(attribute, value);
            case "filter":
                if (attribute.equals("archive")) {
                    experimentList = dao.filterArchive(value);
                }
                experimentList = dao.filter(attribute, value);
            case "search":
                experimentList = dao.filterSearch("%" + attribute + "%");
            case "vaste dienst":
                experimentList = dao.orderByDiensten(attribute, value);
        }

        dao.close();
        return experimentList;
    }


    public List<Experiment> searchDienst(String term) {
        term = "%" + term + "%";
        List<Experiment> experimentList = this.dao.dienstSearch(term);
        this.dao.close();
        return experimentList;
    }
}
