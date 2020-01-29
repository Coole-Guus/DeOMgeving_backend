package org.example.persistence;


import org.example.model.Experiment;
import org.example.model.ExperimentStats;
import org.example.persistence.mapper.ExperimentMapper;
import org.example.persistence.mapper.ExperimentStatsMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@UseStringTemplate3StatementLocator
@Singleton

public interface ExperimentStatsDAO {

    @SqlQuery("CALL `procedure_experiment_stats`(:date);")
    @Mapper(ExperimentStatsMapper.class)
    public ExperimentStats getStats(@Bind("date") String date);

    @SqlQuery("CALL `procedure_experiment_gewijzigd_sinds`(:date);")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> getExperimenten(@Bind("date") String date);

    @SqlQuery("CALL `procedure_diensten_stats`(:date);")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> getVasteDiensten(@Bind("date") String date);

    void close();
}
