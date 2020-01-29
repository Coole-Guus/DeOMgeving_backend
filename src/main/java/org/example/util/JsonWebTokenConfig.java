package org.example.util;

public class JsonWebTokenConfig {
    private String issuer;
    private int expireDate;
    private String secret;
    private int jwtRefreshLeeway;

    public String getIssuer() {
        return issuer;
    }

    public int getExpireDate() {
        return expireDate;
    }

    public String getSecret() {
        return secret;
    }

    public int getJwtRefreshLeeway() {
        return jwtRefreshLeeway;
    }
}
