package org.example;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.example.auth.AuthenticationFilter;
import org.example.persistence.ExperimentDAO;
import org.example.persistence.ExperimentDetailsDAO;
import org.example.persistence.UpdateMessageDAO;
import org.example.persistence.UserDAO;
import org.example.service.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.Arrays;
import java.util.EnumSet;

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
        final FilterRegistration.Dynamic cors =
                env.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "*");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter("Access-Control-Allow-Origin", "*");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        registerInjections(config, env);
        env.jersey().packages("org.example.resource");
        env.jersey().register(new AuthenticationFilter(config));
    }

    private void registerInjections(AppConfiguration config, Environment env) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, config.getDataSourceFactory(), "postgresql");

        env.jersey().register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(jdbi.onDemand(UserDAO.class)).to(UserDAO.class);
                bind(jdbi.onDemand(ExperimentDAO.class)).to(ExperimentDAO.class);
                bind(jdbi.onDemand(ExperimentDetailsDAO.class)).to(ExperimentDetailsDAO.class);
                bind(jdbi.onDemand(UpdateMessageDAO.class)).to(UpdateMessageDAO.class);
                bind(AuthService.class).to(AuthService.class);
                bind(ExperimentDetailsService.class).to(ExperimentDetailsService.class);
                bind(ExperimentDetailsService.class).to(ExperimentDetailsService.class);
                bind(UserService.class).to(UserService.class);
                bind(ExperimentService.class).to(ExperimentService.class);
                bind(config).to(AppConfiguration.class);
                bind(UpdateMessageService.class).to(UpdateMessageService.class);

            }
        });
    }
}
