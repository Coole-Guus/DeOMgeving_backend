package org.example.persistence;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.example.AppConfiguration;
import org.skife.jdbi.v2.DBI;

public class DAOFactory {

    private final DBI jdbi;

    public DAOFactory(DataSourceFactory dataSource, Environment env) {
        final DBIFactory factory = new DBIFactory();
        this.jdbi = factory.build(env, dataSource, "postgresql");
    }

    public <T> T onDemand(Class<T> dao) {

        return jdbi.onDemand(dao);
    }

}
