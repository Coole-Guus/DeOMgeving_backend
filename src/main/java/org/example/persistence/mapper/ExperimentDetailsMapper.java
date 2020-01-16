package org.example.persistence.mapper;

import org.example.model.Experiment;
import org.example.model.ExperimentDetails;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimentDetailsMapper implements ResultSetMapper<ExperimentDetails> {
    @Override
    public ExperimentDetails map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        ExperimentDetails mappedExperimentDetails = new ExperimentDetails();
        mappedExperimentDetails.setExperimentId(r.getInt("experiment_ID"));
        mappedExperimentDetails.setNetwerk(r.getString("netwerk"));
        mappedExperimentDetails.setStatus(r.getString("status"));
        mappedExperimentDetails.setStatus_kleur(r.getString("status_kleur"));
        mappedExperimentDetails.setKosten_inovatie(r.getString("kosten_inovatie"));
        mappedExperimentDetails.setKosten_anders(r.getString("kosten_anders"));
        mappedExperimentDetails.setDoorlooptijd(r.getString("doorlooptijd"));
        mappedExperimentDetails.setBeschrijving(r.getString("beschrijving"));
        mappedExperimentDetails.setVoortgang(r.getString("voortgang"));
        mappedExperimentDetails.setArchief_type(r.getString("archief_type"));
        return mappedExperimentDetails;
    }
}
