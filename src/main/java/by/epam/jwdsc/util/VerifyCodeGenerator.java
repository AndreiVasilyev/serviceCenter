package by.epam.jwdsc.util;

import java.util.Random;

/**
 * The type Verify code generator.
 */
public final class VerifyCodeGenerator {
    private static final Random random = new Random();

    private VerifyCodeGenerator() {
    }

    /**
     * Generate code string.
     *
     * @return the string
     */
    public static String generateCode() {
        int randomValue = random.nextInt(100000);
        String code = String.format("%05d", randomValue);
        return code;
    }
}
