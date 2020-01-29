package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginCredentials implements Credentials {

    @JsonProperty
    @NotNull
    @Length(max = 191)
    private String email;

    @JsonProperty
    @NotNull
    @Length(max = 255)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
