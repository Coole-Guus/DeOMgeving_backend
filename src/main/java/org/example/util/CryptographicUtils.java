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

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[PASSWORD_HASH_LENGTH];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String salt = encoder.encodeToString(bytes).substring(0, PASSWORD_HASH_LENGTH);
        return salt;
    }

}
