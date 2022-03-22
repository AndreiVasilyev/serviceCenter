package by.epam.jwdsc.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class PasswordHashGenerator {

    private static PasswordHashGenerator instance;
    private static final String ALGORITHM = "SHA-256";
    public static final String SALT_DELIMITER = ":";
    private static final String PASSWORD_STRING_TEMPLATE = "%02x";

    private PasswordHashGenerator() {
    }

    public static PasswordHashGenerator getInstance() {
        if (instance == null) {
            instance = new PasswordHashGenerator();
        }
        return instance;
    }

    public String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
        SecureRandom secureRandom = new SecureRandom();
        byte[] saltBytes = new byte[16];
        secureRandom.nextBytes(saltBytes);
        String salt = arrayByteToString(saltBytes);
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
        String passwordToEncrypt = password.concat(salt);
        byte[] encryptedPasswordBytes = messageDigest.digest(passwordToEncrypt.getBytes(StandardCharsets.UTF_8));
        String encryptedPassword = arrayByteToString(encryptedPasswordBytes);
        StringBuilder result = new StringBuilder(salt);
        result.append(SALT_DELIMITER);
        result.append(encryptedPassword);
        return result.toString();
    }

    public String hash(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
        String passwordToEncrypt = password.concat(salt);
        byte[] encryptedPasswordBytes = messageDigest.digest(passwordToEncrypt.getBytes(StandardCharsets.UTF_8));
        String encryptedPassword = arrayByteToString(encryptedPasswordBytes);
        StringBuilder result = new StringBuilder(salt);
        result.append(SALT_DELIMITER);
        result.append(encryptedPassword);
        return result.toString();
    }

    private String arrayByteToString(byte[] array) {
        StringBuilder result = new StringBuilder();
        for (byte byteValue : array) {
            result.append(String.format(PASSWORD_STRING_TEMPLATE, byteValue));
        }
        return result.toString();
    }
}
