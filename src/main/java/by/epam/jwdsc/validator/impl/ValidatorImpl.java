package by.epam.jwdsc.validator.impl;

import by.epam.jwdsc.validator.Validator;

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



}
