package org.example.service;

import org.example.model.ExperimentStats;
import org.example.persistence.ExperimentStatsDAO;

import javax.inject.Inject;
import java.util.List;

public class ExperimentStatsService {

    private final ExperimentStatsDAO experimentStatsDAO;

    @Inject
    public ExperimentStatsService( ExperimentStatsDAO experimentStatsDAO){
        this.experimentStatsDAO = experimentStatsDAO;
    }

    public List<ExperimentStats> getStats(){
        List<ExperimentStats>experimentStatsList = experimentStatsDAO.getStats();
        return experimentStatsList;

    }
}
