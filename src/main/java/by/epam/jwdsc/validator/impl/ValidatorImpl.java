package by.epam.jwdsc.validator.impl;

import by.epam.jwdsc.entity.OrderStatus;
import by.epam.jwdsc.entity.RepairLevel;
import by.epam.jwdsc.entity.UserRole;
import by.epam.jwdsc.entity.dto.EmployeeParameters;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.entity.dto.SparePartData;
import by.epam.jwdsc.service.EntityMapper;
import by.epam.jwdsc.validator.Validator;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

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
    public boolean isUnrequitedEmailValid(String email) {
        boolean result = false;
        if (email != null) {
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
    public boolean isDateValid(String date) {
        boolean result = false;
        if (date != null) {
            result = date.matches(DATE_REGEX);
        }
        return result;
    }

    @Override
    public boolean isOrderStatusValid(String orderStatus) {
        boolean result = false;
        if (orderStatus != null) {
            result = Arrays.stream(OrderStatus.values())
                    .anyMatch(t -> t.name().equals(orderStatus.toUpperCase()));
        }
        return result;
    }

    @Override
    public boolean isRepairLevelValid(String repairLevel) {
        boolean result = true;
        if (repairLevel != null && !repairLevel.isBlank()) {
            result = Arrays.stream(RepairLevel.values())
                    .anyMatch(t -> t.name().equals(repairLevel.toUpperCase()));
        }
        return result;
    }

    @Override
    public boolean isWorkDescriptionValid(String workDescription) {
        boolean result = false;
        if (workDescription != null) {
            result = workDescription.matches(WORK_DESCRIPTION_REGEX);
        }
        return result;
    }

    @Override
    public boolean isSparePartsValid(String spareParts) {
        boolean result = false;
        if (spareParts != null) {
            result = spareParts.matches(SPARE_PARTS_REGEX);
        }
        return result;
    }

    @Override
    public boolean isUserRoleValid(String userRole) {
        boolean result = false;
        if (userRole != null) {
            result = Arrays.stream(UserRole.values())
                    .anyMatch(t -> t.name().equals(userRole.toUpperCase()));
        }
        return result;
    }

    @Override
    public boolean isPartNumberValid(String partNumber) {
        boolean result = false;
        if (partNumber != null) {
            result = partNumber.matches(PART_NUMBER_REGEX);
        }
        return result;
    }

    @Override
    public boolean isPartNameValid(String name) {
        boolean result = false;
        if (name != null) {
            result = name.matches(PART_NAME_REGEX);
        }
        return result;
    }

    @Override
    public boolean isPartDescriptionValid(String description) {
        boolean result = false;
        if (description != null) {
            result = description.matches(PART_DESCRIPTION_REGEX);
        }
        return result;
    }

    @Override
    public boolean isPartCostValid(String cost) {
        boolean result = false;
        if (cost != null) {
            result = cost.matches(PART_COST_REGEX);
        }
        return result;
    }

    @Override
    public boolean isContractValid(String isContract) {
        boolean result = false;
        if (isContract != null) {
            result = isContract.matches(IS_CONTRACT_REGEX);
        }
        return result;
    }


    @Override
    public boolean isNewOrderDataValid(NewOrderData newOrderData) {
        return (isNewOrderNumberValid(newOrderData.getOrderNumber()) && isDeviceNameValid(newOrderData.getDeviceName())
                && isIdValid(newOrderData.getDeviceId()) && isCompanyNameValid(newOrderData.getCompanyName())
                && isIdValid(newOrderData.getCompanyId()) && isModelValid(newOrderData.getModel())
                && isSerialValid(newOrderData.getSerial()) && isIdValid(newOrderData.getClientId())
                && isUnrequitedEmailValid(newOrderData.getEmail()) && isFirstNameValid(newOrderData.getFirstName())
                && isSecondNameValid(newOrderData.getSecondName()) && isPatronymicValid(newOrderData.getPatronymic())
                && isRequiredPhoneNumberValid(newOrderData.getPhoneFirst()) && isPhoneNumberValid(newOrderData.getPhoneSecond())
                && isPhoneNumberValid(newOrderData.getPhoneThird()) && isCountryValid(newOrderData.getCountry())
                && isPostcodeValid(newOrderData.getPostcode()) && isStateValid(newOrderData.getState())
                && isRegionValid(newOrderData.getRegion()) && isCityValid(newOrderData.getCity())
                && isStreetValid(newOrderData.getStreet()) && isHouseNumberValid(newOrderData.getHouseNumber())
                && isApartmentNumberValid(newOrderData.getApartmentNumber()) && isNoteValid(newOrderData.getNote()));
    }

    @Override
    public boolean isOrderDataValid(OrderData orderData) {
        return (isOrderNumberValid(orderData.getOrderNumber()) && isDeviceNameValid(orderData.getDeviceName())
                && isIdValid(orderData.getDeviceId()) && isCompanyNameValid(orderData.getCompanyName())
                && isIdValid(orderData.getCompanyId()) && isModelValid(orderData.getModel()) && isIdValid(orderData.getId())
                && isSerialValid(orderData.getSerial()) && isIdValid(orderData.getClientId())
                && isUnrequitedEmailValid(orderData.getEmail()) && isFirstNameValid(orderData.getFirstName())
                && isSecondNameValid(orderData.getSecondName()) && isPatronymicValid(orderData.getPatronymic())
                && isRequiredPhoneNumberValid(orderData.getPhoneFirst()) && isPhoneNumberValid(orderData.getPhoneSecond())
                && isPhoneNumberValid(orderData.getPhoneThird()) && isCountryValid(orderData.getCountry())
                && isPostcodeValid(orderData.getPostcode()) && isStateValid(orderData.getState())
                && isRegionValid(orderData.getRegion()) && isCityValid(orderData.getCity())
                && isStreetValid(orderData.getStreet()) && isHouseNumberValid(orderData.getHouseNumber())
                && isApartmentNumberValid(orderData.getApartmentNumber()) && isNoteValid(orderData.getNote())
                && isIdValid(orderData.getAcceptedEmployeeId()) && isIdValid(orderData.getCompletedEmployeeId())
                && isIdValid(orderData.getWorkPriceId()) && isDateValid(orderData.getCreationDate())
                && isDateValid(orderData.getCompletionDate()) && isDateValid(orderData.getIssueDate())
                && isOrderStatusValid(orderData.getOrderStatus()) && isRepairLevelValid(orderData.getRepairLevel())
                && isWorkDescriptionValid(orderData.getWorkDescription()) && isSparePartsValid(orderData.getSpareParts()));
    }

    public boolean isEmployeeValid(EmployeeParameters employeeParameters, String password, String passwordConfirm) {
        if (isPasswordValid(password) && isPasswordValid(passwordConfirm) && password.equals(passwordConfirm)) {
            String[] phones = employeeParameters.getPhones().split(EntityMapper.PHONES_DELIMITER);
            String phone1 = phones[0];
            String phone2 = phones.length > 1 ? phones[1] : Strings.EMPTY;
            String phone3 = phones.length > 2 ? phones[2] : Strings.EMPTY;
            return (isLoginValid(employeeParameters.getLogin()) && isUserRoleValid(employeeParameters.getUserRole())
                    && isUnrequitedEmailValid(employeeParameters.getEmail()) && isFirstNameValid(employeeParameters.getFirstName())
                    && isSecondNameValid(employeeParameters.getSecondName()) && isPatronymicValid(employeeParameters.getPatronymic())
                    && isPostcodeValid(employeeParameters.getPostcode()) && isCountryValid(employeeParameters.getCountry())
                    && isStateValid(employeeParameters.getState()) && isRegionValid(employeeParameters.getRegion())
                    && isCityValid(employeeParameters.getCity()) && isStreetValid(employeeParameters.getStreet())
                    && isHouseNumberValid(employeeParameters.getHouseNumber()) && isApartmentNumberValid(employeeParameters.getApartmentNumber())
                    && isRequiredPhoneNumberValid(phone1) && isPhoneNumberValid(phone2) && isPhoneNumberValid(phone3));
        }
        return false;
    }

    public boolean isPartValid(SparePartData sparePartData) {
        return (isPartNumberValid(sparePartData.getPartNumber()) && isPartNameValid(sparePartData.getName())
                && isPartDescriptionValid(sparePartData.getDescription()) && isPartCostValid(sparePartData.getCost()));
    }
}


