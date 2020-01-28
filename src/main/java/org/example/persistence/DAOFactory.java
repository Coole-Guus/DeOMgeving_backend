package org.example.persistence;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.example.AppConfiguration;
import org.skife.jdbi.v2.DBI;

public class DAOFactory {

    private final DataSourceFactory dataSource;
    private final DBIFactory factory;
    private final Environment env;
    private final String driverName;

    public DAOFactory(DataSourceFactory dataSource, Environment env){
        this.dataSource = dataSource;
        this.factory = new DBIFactory();
        this.driverName = "postgresql";
        this.env = env;
    }

    public <T> T onDemand(Class<T> dao ){
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, this.dataSource, "postgresql");
        return jdbi.onDemand(dao);
    }
}
