package org.example.persistence;

import org.example.model.Experiment;
import org.example.persistence.mapper.ExperimentMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import javax.inject.Singleton;
import java.util.List;
@UseStringTemplate3StatementLocator
@Singleton
public interface ExperimentDAO {


    @SqlQuery("SELECT * FROM experiment INNER JOIN experiment_details ON experiment.experiment_ID=experiment_details.experiment_ID WHERE archief_type IS NULL AND fase != 'Vaste dienst';")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> getAll();

    //--------------------Order BY--------------------

    @SqlQuery( "SELECT LAST_INSERT_ID()")
    public int getLastID();

    @SqlQuery("SELECT * FROM experiment INNER JOIN experiment_details ON experiment.experiment_ID=experiment_details.experiment_ID WHERE archief_type IS NULL AND fase != 'Vaste dienst' ORDER BY <attribute> <order>;")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> orderBy(@Define("attribute") String attribute, @Define("order") String order);


    //--------------------FILTERS--------------------
    @SqlQuery("SELECT * FROM experiment INNER JOIN experiment_details ON experiment.experiment_ID=experiment_details.experiment_ID WHERE archief_type IS NULL AND fase != 'Vaste dienst' AND <filter> = :value;")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> filter(@Define("filter") String filter, @Bind("value") String value);


    //--------------------ARCHIVE--------------------

    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment.experiment_ID, experiment.status_kleur FROM experiment INNER JOIN experiment_details ON experiment.experiment_ID=experiment_details.experiment_ID\n" +
            "WHERE archief_type = :type AND fase != 'Vaste dienst';")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> filterArchive(@Bind("type") String type);

    //--------------------SEARCH--------------------

    //Filter search bar
    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase, wijziging_datum, experiment_ID, status_kleur FROM experiment WHERE archief_type IS NULL AND experiment_naam LIKE :searchString OR experiment_leider_primair LIKE :searchString OR experiment_leider_secundair LIKE :searchString AND fase != 'Vaste dienst';")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> filterSearch(@Bind("searchString")String searchString);

    //--------------------VASTE DIENSTEN--------------------

    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment WHERE fase = 'Vaste dienst' ORDER BY <attribute> <order>;")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> orderByDiensten(@Define("attribute") String attribute, @Define("order") String order);

    //--------------------EXPERIMENTEN STATS--------------------





    @SqlQuery("SELECT * FROM experiment WHERE experiment_ID = :id")
    @Mapper(ExperimentMapper.class)
    public Experiment find(@Bind("id") int id);

    @SqlUpdate("DELETE FROM experiment WHERE experiment_ID = :id")
    public void delete(@Bind("id")int id);

    @SqlUpdate("INSERT INTO experiment (experiment_naam, wijziging_datum, fase, experiment_leider_primair, experiment_leider_secundair, status_kleur) " +
            "VALUES (:experiment_naam, :wijziging_datum, :fase, :experiment_leider_primair, :experiment_leider_secundair, :color);")
    public void add(@BindBean Experiment newExperiment);

    @SqlUpdate("UPDATE experiment SET " +
            "experiment_naam = :experiment_naam," +
            "wijziging_datum = :wijziging_datum," +
            "fase = :fase," +
            "status_kleur=:color," +
            "experiment_leider_primair = :experiment_leider_primair, " +
            "experiment_leider_secundair = :experiment_leider_secundair " +
            "WHERE experiment_ID = :id")
    public void update(@Bind("id") int id, @BindBean Experiment updatedExperiment);

    //Select last insert experiment_id
    @SqlQuery("SELECT LAST_INSERT_ID();")
    int lastInsert();
}
