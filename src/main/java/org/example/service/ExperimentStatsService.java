package org.example.service;

import org.example.model.Experiment;
import org.example.model.ExperimentStats;
import org.example.persistence.DAOFactory;
import org.example.persistence.ExperimentStatsDAO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExperimentStatsService extends BaseService<Experiment> {

    private final ExperimentStatsDAO experimentStatsDAO;

    @Inject
    public ExperimentStatsService( DAOFactory factory){
        this.experimentStatsDAO = factory.onDemand(ExperimentStatsDAO.class);
    }

    public ExperimentStats getStats(String date){
        ExperimentStats experimentStats = experimentStatsDAO.getStats(date);
        List<Experiment> experiments = experimentStatsDAO.getExperimenten(date);
        experimentStats.setGewijzigdeExperimenten(experiments);
        experimentStats.setGewijzigdeDiensten(experimentStatsDAO.getVasteDiensten(date));
        experimentStatsDAO.close();
        return experimentStats;
    }
}
