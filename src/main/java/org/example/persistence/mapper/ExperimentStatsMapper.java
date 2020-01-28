package org.example.persistence.mapper;

import org.example.model.Experiment;
import org.example.model.ExperimentStats;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimentStatsMapper implements ResultSetMapper<ExperimentStats> {
    @Override
    public ExperimentStats map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        ExperimentStats mappedExperimetnStats = new ExperimentStats();
        mappedExperimetnStats.setLabUitStats(resultSet.getInt("lab_uit_stats"));
        mappedExperimetnStats.setLabInStats(resultSet.getInt("lab_in_stats"));
        mappedExperimetnStats.setIdeeStats(resultSet.getInt("idee_stats"));
        mappedExperimetnStats.setExperimentenStats(resultSet.getInt("experimenten_stats"));
        mappedExperimetnStats.setVasteDienstStats(resultSet.getInt("vaste_dienst_stats"));
        mappedExperimetnStats.setGroenStats(resultSet.getInt("groen_stats"));
        mappedExperimetnStats.setRoodStats(resultSet.getInt("oranje_stats"));
        mappedExperimetnStats.setOranjeStats(resultSet.getInt("rood_stats"));
        mappedExperimetnStats.setGewijzig(resultSet.getInt("gewijzig_laatste_30_dagen_stats"));
        return mappedExperimetnStats;
    }
}
