package org.example;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class DeOMgevingBackendApplication extends Application<DeOMgevingBackendConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DeOMgevingBackendApplication().run(args);
    }

    @Override
    public String getName() {
        return "DeOMgeving_backend";
    }

    @Override
    public void initialize(final Bootstrap<DeOMgevingBackendConfiguration> bootstrap) {

        // voegt guicey toe aan dropwizard, alle dropwizard componenten worden hierdoor automatisch toegevoegd
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .build());

        // voegt liquibase toe aan dropwizard.
        bootstrap.addBundle(new MigrationsBundle<DeOMgevingBackendConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(DeOMgevingBackendConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final DeOMgevingBackendConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
