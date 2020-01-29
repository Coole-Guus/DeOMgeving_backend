package org.example;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.example.util.JsonWebTokenConfig;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class AppConfiguration extends Configuration {

    @NotNull
    @JsonProperty
    private JsonWebTokenConfig jsonWebToken = new JsonWebTokenConfig();

    /*  |------------------------------------------------------------|
        |                WARNING DONT CHANGE ONCE SET                |
        |------------------------------------------------------------|
        |          Alert should not be changed once used!            |
        |   this is the secret added to salt when hasing password,   |
        |      once changed registered user cannot be logged in.     |
        |------------------------------------------------------------|
     */
    private String passwordHash = "7CBFA3B36A8FB1986814E5AA1E7875226AA36C5913432C99848A71DC3233DAE6";


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

    public JsonWebTokenConfig getJwtConfig() {
        return jsonWebToken;
    }

    public DataSourceFactory getDatabase() {
        return database;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}

