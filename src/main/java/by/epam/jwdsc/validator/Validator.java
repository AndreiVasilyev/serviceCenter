package by.epam.jwdsc.validator;

public interface Validator {
    boolean isEmailValid(String email);

    boolean isOrderNumberValid(String orderNumber);

    boolean isCodeValid(String code);
}
