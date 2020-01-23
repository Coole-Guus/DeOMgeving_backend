package org.example;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.example.util.Secrets;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashMap;

public class AppConfiguration extends Configuration {

    @NotNull
    @JsonProperty
    private Secrets secrets = new Secrets();

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public Secrets getSecrets() {
        return secrets;
    }

    public DataSourceFactory getDatabase() {
        return database;
    }
}

