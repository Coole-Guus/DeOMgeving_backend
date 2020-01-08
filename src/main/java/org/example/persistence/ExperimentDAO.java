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


    @SqlQuery("SELECT * FROM experiment")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> getAll();


    //--------------------Order BY--------------------

    // OLD
//    //ORDER name Asc
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment ORDER BY experiment_naam ASC;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> orderNameAsc();
//
//    //ORDER name Desc
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment ORDER BY experiment_naam DESC;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> orderNameDesc();
//
//    //ORDER lieder Asc
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment ORDER BY experiment_leider_primair ASC;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> orderLeiderAsc();
//
//    //ORDER lieder Desc
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment ORDER BY experiment_leider_primair DESC;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> orderLeiderDesc();
//
//    //ORDER edited Asc
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment ORDER BY wijziging_datum ASC;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> orderEditedAsc();
//
//    //ORDER edited Desc
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment ORDER BY wijziging_datum DESC;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> orderEditedDesc();

    @SqlQuery( "SELECT LAST_INSERT_ID()")
    public int getLastID();

    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment ORDER BY <attribute> <order>;")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> orderBy(@Define("attribute") String attribute, @Define("order") String order);



    //--------------------FILTERS--------------------

    // OLD
//    //Filter Idee
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment WHERE fase = 'Idee' ;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> filterIdee();
//
//    //Filter lab in
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment WHERE fase = 'Lab in' ;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> filterLabIn();
//
//    //Filter lab uit
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment WHERE fase = 'Lab uit' ;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> filterLabUit();
//
//    //Filter status_kleur Groen
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment WHERE status_kleur = 'Groen' ;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> filterGreen();
//
//    //Filter status_kleur Oranje
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment WHERE status_kleur = 'Oranje' ;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> filterOrange();
//
//    //Filter status_kleur Rood
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment WHERE status_kleur = 'Rood' ;")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> filterRed();

    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment_ID, status_kleur FROM experiment WHERE <filter> = :value ;")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> filter(@Define("filter") String filter, @Bind("value") String value);

    //--------------------ARCHIVE--------------------

    // OLD
//    //Filter archive_type Hall of Fame
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment.experiment_ID, experiment.status_kleur FROM experiment INNER JOIN experiment_details ON experiment.experiment_ID=experiment_details.experiment_ID\n" +
//            "WHERE archief_type = 'HoF';")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> filterHoF();
//
//    //Filter archive_type Graveyard
//    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment.experiment_ID, experiment.status_kleur FROM experiment INNER JOIN experiment_details ON experiment.experiment_ID=experiment_details.experiment_ID\n" +
//            "WHERE archief_type = 'GY';")
//    @Mapper(ExperimentMapper.class)
//    public List<Experiment> filterGY();

    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase ,wijziging_datum, experiment.experiment_ID, experiment.status_kleur FROM experiment INNER JOIN experiment_details ON experiment.experiment_ID=experiment_details.experiment_ID\n" +
            "WHERE archief_type = :type;")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> filterArchive(@Bind("type") String type);

    //--------------------SEARCH--------------------

    //Filter search bar
    @SqlQuery("SELECT experiment_naam, experiment_leider_primair, experiment_leider_secundair, fase, wijziging_datum, experiment_ID, status_kleur FROM experiment WHERE experiment_naam LIKE :searchString OR experiment_leider_primair LIKE :searchString OR experiment_leider_secundair LIKE :searchString;")
    @Mapper(ExperimentMapper.class)
    public List<Experiment> filterSearch(@Bind("searchString")String searchString);







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
            "experiment_leider_primair = :experiment_leider_primair " +
            "experiment_leider_secundair = :experiment_leider_secundair " +
            "WHERE experiment_ID = :id")
    public void update(@Bind("id") int id, @BindBean Experiment updatedExperiment);

    //Select last insert experiment_id
    @SqlQuery("SELECT LAST_INSERT_ID();")
    int lastInsert();
}
