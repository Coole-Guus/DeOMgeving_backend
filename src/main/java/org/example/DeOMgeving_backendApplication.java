package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DeOMgeving_backendApplication extends Application<DeOMgeving_backendConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DeOMgeving_backendApplication().run(args);
    }

    @Override
    public String getName() {
        return "DeOMgeving_backend";
    }

    @Override
    public void initialize(final Bootstrap<DeOMgeving_backendConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DeOMgeving_backendConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
