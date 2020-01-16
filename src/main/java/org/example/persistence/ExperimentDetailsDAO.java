package org.example.persistence;

import org.example.model.ExperimentDetails;
import org.example.persistence.mapper.ExperimentDetailsMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import javax.inject.Singleton;

@UseStringTemplate3StatementLocator
@Singleton
public interface ExperimentDetailsDAO {

    @SqlQuery("SELECT * FROM experiment_details WHERE experiment_ID = :id")
    @Mapper(ExperimentDetailsMapper.class)
    public ExperimentDetails find(@Bind("id") int id);

    @SqlUpdate("UPDATE experiment_details SET" +
            "netwerk = :netwerk," +
            "status = :status," +
            "status_kleur = :status_kleur," +
            "kosten_inovatie = :kosten_inovatie," +
            "kosten_anders = :kosten_anders," +
            "doorlooptijd = :doorlooptijd," +
            "beschrijving = :beschrijving," +
            "voortgang = :voortgang," +
            "archief_type = :archief_type" +
            " WHERE experiment_ID = :id")
    public void updateExperimentDetails(@Bind("id") int id, @BindBean ExperimentDetails experimentDetails);

    @SqlUpdate("INSERT INTO experiment_details " +
            "(netwerk, status, status_kleur, kosten_inovatie, kosten_anders, doorlooptijd, beschrijving, voortgang, archief_type) " +
            "VALUES " +
            "(:netwerk, :status, :status_kleur, :kosten_inovatie, :kosten_anders, :doorlooptijd, :beschrijving, :voortgang, :archief_type) ")
    public int addExperimentDetails( @BindBean ExperimentDetails experimentDetails);

    @SqlUpdate("DELETE FROM experiment_details WHERE experiment_id = :id")
    public void deleteExperimentDetails(@Bind("id") int id);
}
