package org.example;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.persistence.UserDAO;
import org.example.service.LoginService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.skife.jdbi.v2.DBI;

public class App extends Application<AppConfiguration> {

    public static void main(final String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "DeOMgeving_backend";
    }

    @Override
    public void initialize(final Bootstrap<AppConfiguration> bootstrap) {
        // voegt liquibase toe aan dropwizard.
        bootstrap.addBundle(new MigrationsBundle<AppConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

    }

    @Override
    public void run(final AppConfiguration config, final Environment env) {

        registerInjections(config, env);
        env.jersey().packages("org.example.service");
        env.jersey().packages("org.example.resource");
    }

    private void registerInjections(AppConfiguration config, Environment env) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, config.getDataSourceFactory(), "postgresql");

        env.jersey().register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(jdbi.onDemand(UserDAO.class)).to(UserDAO.class);
                bind(LoginService.class).to(LoginService.class);
            }

        });
    }
}
