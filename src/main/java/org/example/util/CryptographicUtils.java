package org.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.example.model.User;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

public class CryptographicUtils {

    public static final int PASSWORD_HASH_LENGTH = 512;

    public static int generateFourDigitNumber() {
        final int length = 9;
        int randNum = 0;
        while (randNum < Math.pow(10, length - 1)) {
            randNum = (int) Math.floor(Math.random() * Math.pow(10, length));
        }

        return randNum;
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[PASSWORD_HASH_LENGTH];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String salt = encoder.encodeToString(bytes).substring(0, PASSWORD_HASH_LENGTH);
        return salt;
    }


    public static String buildJWTToken(User user, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Date expireDate = createExpireDate(60);
        return JWT.create()
                .withIssuer("De_omgeving")
                .withIssuedAt(new Date())
                .withClaim("name", user.getName())
                .withClaim("role", user.getRole())
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    private static Date createExpireDate(int minutes) {
        long MINUTES_TO_SECONDS = 60 * minutes;
        Instant instant = Instant.now().plusSeconds(MINUTES_TO_SECONDS);
        return Date.from(instant);
    }
}
