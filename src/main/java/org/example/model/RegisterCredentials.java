package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Stefan
 */
public class RegisterCredentials {

    @JsonProperty
    @NotNull
    @Length(max = 255)
    private String email;

    @NotNull
    @Length(max = 255)
    @JsonProperty
    private String password;

    @NotNull
    @Length(max = 255)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
