package org.example.service;

import org.example.model.Experiment;
import org.example.persistence.ExperimentDAO;
import org.skife.jdbi.v2.DBI;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

public class ExperimentService extends BaseService<Experiment> {

    private final ExperimentDAO dao;
    private final DBI database;

    @Inject
    public ExperimentService(DBI jdbi) {
        this.database = jdbi;
        dao = this.database.onDemand(ExperimentDAO.class);

    }

    public Collection<Experiment> getAll() {
        return dao.getAll();
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

    public List<Experiment> orderNameAsc(){
        return dao.orderNameAsc();
    }

    public List<Experiment> orderNameDesc(){
        return dao.orderNameDesc();
    }

    public List<Experiment> orderLiederAsc(){
        return dao.orderLeiderAsc();
    }

    public List<Experiment> orderLiederDesc(){
        return dao.orderLeiderDesc();
    }

    public List<Experiment> orderEditedAsc(){
        return dao.orderEditedAsc();
    }

    public List<Experiment> orderEditedDesc(){
        return dao.orderEditedDesc();
    }

    //--------------------FILTERS--------------------

    public List<Experiment> filterIdee(){
        return dao.filterIdee();
    }

    public List<Experiment> filterLabIn(){
        return dao.filterLabIn();
    }

    public List<Experiment> filterLabUit(){
        return dao.filterLabUit();
    }

    public List<Experiment> filterGreen(){
        return dao.filterGreen();
    }

    public List<Experiment> filterOrange(){
        return dao.filterOrange();
    }

    public List<Experiment> filterRed(){
        return dao.filterRed();
    }

    public List<Experiment> filterHoF(){
        return dao.filterHoF();
    }

    public List<Experiment> filterGY(){
        return dao.filterGY();
    }

    public List<Experiment> filterSearch(String searchString){
        searchString = "%" + searchString + "%";
        return dao.filterSearch(searchString);

    }
    public int getLastID(){
        return dao.getLastID();
    }



}
