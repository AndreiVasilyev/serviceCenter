package by.epam.jwdsc.util;

import java.util.Random;

public final class CodeGenerator {
    private static final Random random = new Random();

    private CodeGenerator() {
    }

    public static String generateCode() {
        int randomValue = random.nextInt(100000);
        String code = String.format("%05d", randomValue);
        return code;
    }
}
