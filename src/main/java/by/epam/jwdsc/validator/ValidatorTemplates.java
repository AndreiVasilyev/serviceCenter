package by.epam.jwdsc.validator;

public final class ValidatorTemplates {

    public static final String EMAIL_REGEX = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
    public static final int EMAIL_MIN_LENGTH = 8;
    public static final String CODE_REGEX = "\\b\\d{5}\\b";
    public static final String ORDER_NUMBER_REGEX = "[PS]\\d{1,5}$";

    private ValidatorTemplates() {
    }
}
