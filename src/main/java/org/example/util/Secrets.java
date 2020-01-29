package org.example.util;

public class Secrets {
    private String passwordHash;
    private String jwtSecret;
    private int jwtRefreshLeeway;

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public int getJwtRefreshLeeway() {
        return jwtRefreshLeeway;
    }
}
