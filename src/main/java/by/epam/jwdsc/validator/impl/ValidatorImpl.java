package by.epam.jwdsc.validator.impl;

import by.epam.jwdsc.validator.Validator;

import static by.epam.jwdsc.validator.ValidatorTemplates.*;

public class ValidatorImpl implements Validator {

    private static Validator instance;

    private ValidatorImpl() {
    }

    public static Validator getInstance() {
        if (instance == null) {
            instance = new ValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isEmailValid(String email) {
        boolean result = false;
        if (email != null && email.length() >= EMAIL_MIN_LENGTH) {
            result = email.matches(EMAIL_REGEX);
        }
        return result;
    }

    @Override
    public boolean isOrderNumberValid(String orderNumber) {
        boolean result = false;
        if (orderNumber != null && !orderNumber.isBlank()) {
            result = orderNumber.matches(ORDER_NUMBER_REGEX);
        }
        return result;
    }

    @Override
    public boolean isCodeValid(String code) {
        boolean result = false;
        if (code != null && !code.isBlank()) {
            result = code.matches(CODE_REGEX);
        }
        return result;
    }

    @Override
    public boolean isLoginValid(String login) {
        boolean result = false;
        if (login != null && !login.isBlank()) {
            result = login.matches(LOGIN_REGEX);
        }
        return result;
    }

    @Override
    public boolean isPasswordValid(String password) {
        boolean result = false;
        if (password != null && !password.isBlank()) {
            result = password.matches(PASSWORD_REGEX);
        }
        return result;
    }


}
