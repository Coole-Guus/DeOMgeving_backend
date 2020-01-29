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
        mappedExperimetnStats.setTotaal(resultSet.getInt("Totaal"));
        mappedExperimetnStats.setAantalExperimenten(resultSet.getInt("Experimenten"));
        mappedExperimetnStats.setAantalIdee(resultSet.getInt("Idee"));
        mappedExperimetnStats.setAantalLabIn(resultSet.getInt("Lab_in"));
        mappedExperimetnStats.setAantalLabUit(resultSet.getInt("Lab_uit"));
        mappedExperimetnStats.setAantalVasteDienst(resultSet.getInt("Vaste_dienst"));
        mappedExperimetnStats.setAantalGroen(resultSet.getInt("Groen"));
        mappedExperimetnStats.setAantalOranje(resultSet.getInt("Oranje"));
        mappedExperimetnStats.setAantalRood(resultSet.getInt("Rood"));
        mappedExperimetnStats.setAantalGewijzigdSinds(resultSet.getInt("Gewijzigd_Laatste_X_Dagen"));
        return mappedExperimetnStats;
    }
}
