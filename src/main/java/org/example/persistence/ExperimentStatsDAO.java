package org.example.persistence;


import org.example.model.ExperimentStats;
import org.example.persistence.mapper.ExperimentStatsMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import javax.inject.Singleton;
import java.util.List;
@UseStringTemplate3StatementLocator
@Singleton

public interface ExperimentStatsDAO {

    @SqlQuery("CALL 'procedure_experiment_stats'();")
    @Mapper(ExperimentStatsMapper.class)
        public List<ExperimentStats> getStats();

}
