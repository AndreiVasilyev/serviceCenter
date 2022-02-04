package by.epam.jwdsc.validator.impl;

import by.epam.jwdsc.entity.dto.NewOrderData;
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

    @Override
    public boolean isNewOrderNumberValid(String orderNumber) {
        boolean result = false;
        if (orderNumber != null && !orderNumber.isBlank()) {
            result = !orderNumber.matches(NEW_ORDER_NUMBER_REGEX);
        }
        return result;
    }

    @Override
    public boolean isDeviceNameValid(String deviceName) {
        boolean result = false;
        if (deviceName != null && !deviceName.isBlank()) {
            result = deviceName.matches(DEVICE_NAME_REGEX);
        }
        return result;
    }

    @Override
    public boolean isCompanyNameValid(String companyName) {
        boolean result = false;
        if (companyName != null) {
            result = companyName.matches(COMPANY_NAME_REGEX);
        }
        return result;
    }

    @Override
    public boolean isIdValid(String id) {
        boolean result = false;
        if (id != null) {
            result = id.matches(ID_REGEX);
        }
        return result;
    }

    @Override
    public boolean isModelValid(String model) {
        boolean result = false;
        if (model != null) {
            result = model.matches(MODEL_REGEX);
        }
        return result;
    }

    @Override
    public boolean isSerialValid(String serial) {
        boolean result = false;
        if (serial != null) {
            result = serial.matches(SERIAL_REGEX);
        }
        return result;
    }

    @Override
    public boolean isFirstNameValid(String firstName) {
        boolean result = false;
        if (firstName != null && !firstName.isBlank()) {
            result = firstName.matches(FIRST_NAME_REGEX);
        }
        return result;
    }

    @Override
    public boolean isSecondNameValid(String secondName) {
        boolean result = false;
        if (secondName != null && !secondName.isBlank()) {
            result = secondName.matches(SECOND_NAME_REGEX);
        }
        return result;
    }

    @Override
    public boolean isPatronymicValid(String patronymic) {
        boolean result = false;
        if (patronymic != null) {
            result = patronymic.matches(PATRONYMIC_REGEX);
        }
        return result;
    }

    @Override
    public boolean isPhoneNumberValid(String phoneNumber) {
        boolean result = false;
        if (phoneNumber != null) {
            result = phoneNumber.matches(PHONE_NUMBER_REGEX);
        }
        return result;
    }

    @Override
    public boolean isRequiredPhoneNumberValid(String phoneNumber) {
        boolean result = false;
        if (phoneNumber != null && !phoneNumber.isBlank()) {
            result = phoneNumber.matches(PHONE_NUMBER_REGEX);
        }
        return result;
    }

    @Override
    public boolean isCountryValid(String country) {
        boolean result = false;
        if (country != null) {
            result = country.matches(COUNTRY_REGEX);
        }
        return result;
    }

    @Override
    public boolean isPostcodeValid(String postcode) {
        boolean result = false;
        if (postcode != null) {
            result = postcode.matches(POSTCODE_REGEX);
        }
        return result;
    }

    @Override
    public boolean isStateValid(String state) {
        boolean result = false;
        if (state != null) {
            result = state.matches(STATE_REGEX);
        }
        return result;
    }

    @Override
    public boolean isRegionValid(String region) {
        boolean result = false;
        if (region != null) {
            result = region.matches(REGION_REGEX);
        }
        return result;
    }

    @Override
    public boolean isCityValid(String city) {
        boolean result = false;
        if (city != null && !city.isBlank()) {
            result = city.matches(CITY_REGEX);
        }
        return result;
    }

    @Override
    public boolean isStreetValid(String street) {
        boolean result = false;
        if (street != null && !street.isBlank()) {
            result = street.matches(STREET_REGEX);
        }
        return result;
    }

    @Override
    public boolean isHouseNumberValid(String houseNumber) {
        boolean result = false;
        if (houseNumber != null && !houseNumber.isBlank()) {
            result = houseNumber.matches(HOUSE_NUMBER_REGEX);
        }
        return result;
    }

    @Override
    public boolean isApartmentNumberValid(String apartmentNumber) {
        boolean result = false;
        if (apartmentNumber != null) {
            result = apartmentNumber.matches(APARTMENT_NUMBER_REGEX);
        }
        return result;
    }

    @Override
    public boolean isNoteValid(String note) {
        boolean result = false;
        if (note != null) {
            result = note.matches(NOTE_REGEX);
        }
        return result;
    }

    @Override
    public boolean isNewOrderDataValid(NewOrderData newOrderData) {
        return (isOrderNumberValid(newOrderData.getOrderNumber()) && isDeviceNameValid(newOrderData.getDeviceName())
                && isIdValid(newOrderData.getDeviceId()) && isCompanyNameValid(newOrderData.getCompanyName())
                && isIdValid(newOrderData.getCompanyId()) && isModelValid(newOrderData.getModel())
                && isSerialValid(newOrderData.getSerial()) && isIdValid(newOrderData.getClientId())
                && isEmailValid(newOrderData.getEmail()) && isFirstNameValid(newOrderData.getFirstName())
                && isSecondNameValid(newOrderData.getSecondName()) && isPatronymicValid(newOrderData.getPatronymic())
                && isRequiredPhoneNumberValid(newOrderData.getPhoneFirst()) && isPhoneNumberValid(newOrderData.getPhoneSecond())
                && isPhoneNumberValid(newOrderData.getPhoneThird()) && isCountryValid(newOrderData.getCountry())
                && isPostcodeValid(newOrderData.getPostcode()) && isStateValid(newOrderData.getState())
                && isRegionValid(newOrderData.getRegion()) && isCityValid(newOrderData.getCity())
                && isStreetValid(newOrderData.getStreet()) && isHouseNumberValid(newOrderData.getHouseNumber())
                && isApartmentNumberValid(newOrderData.getApartmentNumber()) && isNoteValid(newOrderData.getNote()));
    }
}
