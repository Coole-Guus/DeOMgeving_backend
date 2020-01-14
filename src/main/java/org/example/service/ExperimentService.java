package org.example.service;

import org.example.model.Experiment;
import org.example.persistence.ExperimentDAO;
import org.skife.jdbi.v2.DBI;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

public class ExperimentService extends BaseService<Experiment> {

    private final ExperimentDAO dao;

    @Inject
    public ExperimentService(ExperimentDAO dao) {
        this.dao = dao;

    }

    public List<Experiment> getAll() {
        System.out.println("getting");
        List<Experiment> experimentList = dao.getAll();
        System.out.println(experimentList.toString());
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

    //--------------------ORDER BY--------------------

    public List<Experiment> orderBy(String attribute, String order) {
        return dao.orderBy(attribute, order);
    }

    //--------------------FILTERS--------------------

    public List <Experiment> filter(String filter, String value) {
        return dao.filter(filter, value);
    }

    //--------------------ARCHIVE--------------------

    public List<Experiment> archive(String type) {
        return dao.filterArchive(type);
    }

    //--------------------SEARCH--------------------

    public List<Experiment> filterSearch(String searchString){
        searchString = "%" + searchString + "%";
        return dao.filterSearch(searchString);

    }
    public int getLastID(){
        return dao.getLastID();
    }



}
