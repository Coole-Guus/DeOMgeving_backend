package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.validation.OneOf;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class User {

    @NotNull
    private long id;

    @NotNull
    @Length(max = 255)
    private String email;

    @NotNull
    @Length(max = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Length(max = 255)
    private String name;

    @OneOf(value = {"Admin", "Medewerker", "Gebruiker", "Unidentified"})
    private String role;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYY HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createDate;

    @Length(min = 32, max = 32)
    private String token;

    public User(){ }

    public User(long id, String email, String password, String name, String role, Timestamp createDate, String token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.createDate = createDate;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
