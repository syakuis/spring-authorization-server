package io.github.syakuis.authorizationserver.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

/**
 * @author Seok Kyun. Choi.
 * @since 2022-08-28
 */
public final class ClientKeyGenerator {
    private ClientKeyGenerator() {
    }
    private static final char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")
        .toCharArray();

    public static synchronized String clientId() {
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        pbkdf2PasswordEncoder.setAlgorithm(Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA1);
        return pbkdf2PasswordEncoder.encode(UUID.randomUUID().toString());
    }

    public static synchronized String clientSecret() {
        return Base64.getEncoder().encodeToString(RandomStringUtils
            .random(15, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom())
            .getBytes(
                StandardCharsets.UTF_8));
    }
}
