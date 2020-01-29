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
                return dao.filterSearch( "%" + attribute + "%");
            case "vaste dienst":
                System.out.println("attribute: " + attribute + ", value: " + value);
                return dao.orderByDiensten(attribute, value);
            default:
                return null;
        }
    }




}
