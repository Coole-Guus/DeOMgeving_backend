package org.example.service;

import org.example.model.Experiment;
import org.example.persistence.ExperimentDAO;

import javax.inject.Inject;
import java.util.List;

public class ExperimentService extends BaseService<Experiment> {

    private final ExperimentDAO dao;

    @Inject
    public ExperimentService(ExperimentDAO dao) {
        this.dao = dao;

    }

    public List<Experiment> getAll() {
        List<Experiment> experimentList = dao.getAll();
        return experimentList;
    }

    public Experiment find(int id) {
        return requireResult(dao.find(id));
    }

    public int add(Experiment experiment) {
        dao.add(experiment);
        return dao.lastInsert();
    }

    public void update(int id, Experiment experiment) {
        dao.update(id, experiment);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public List<Experiment> selectBy(String operation, String attribute, String value) {
        switch (operation){
            case "order":
                return dao.orderBy(attribute, value);
            case "filter":
                if(attribute.equals("archive")) {
                    return dao.filterArchive(value);
                }
                return dao.filter(attribute, value);
            case "search":
                return dao.filterSearch(attribute);
            default:
                return null;
        }
    }

    public int getLastID(){
        return dao.getLastID();
    }



}
