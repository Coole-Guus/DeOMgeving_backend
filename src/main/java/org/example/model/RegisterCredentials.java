package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Stefan
 */
public class RegisterCredentials implements Credentials {

    @JsonProperty
    @NotNull
    @Size( min = 3, max = 191)
    private String email;

    @NotNull
    @Size( min = 8, max = 255)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,255}$",
            message = "The given password must be in range 8, 255 characters. it requires to have a symbol, number and a capital character.")
    @JsonProperty
    private String password;

    @NotNull
    @Size(min = 3, max = 255 )
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
