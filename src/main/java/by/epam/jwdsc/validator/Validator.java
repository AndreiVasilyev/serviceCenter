package by.epam.jwdsc.validator;

import by.epam.jwdsc.entity.dto.EmployeeParameters;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;

public interface Validator {
    boolean isEmailValid(String email);

    boolean isUnrequitedEmailValid(String email);

    boolean isOrderNumberValid(String orderNumber);

    boolean isCodeValid(String code);

    boolean isLoginValid(String code);

    boolean isPasswordValid(String password);

    boolean isNewOrderNumberValid(String orderNumber);

    boolean isDeviceNameValid(String deviceName);

    boolean isCompanyNameValid(String companyName);

    boolean isIdValid(String companyName);

    boolean isModelValid(String model);

    boolean isSerialValid(String serial);

    boolean isFirstNameValid(String firstName);

    boolean isSecondNameValid(String secondName);

    boolean isPatronymicValid(String patronymic);

    boolean isPhoneNumberValid(String phoneNumber);

    boolean isRequiredPhoneNumberValid(String phoneNumber);

    boolean isCountryValid(String country);

    boolean isPostcodeValid(String postcode);

    boolean isStateValid(String state);

    boolean isRegionValid(String region);

    boolean isCityValid(String city);

    boolean isStreetValid(String street);

    boolean isHouseNumberValid(String houseNumber);

    boolean isApartmentNumberValid(String apartmentNumber);

    boolean isNoteValid(String note);

    boolean isDateValid(String date);

    boolean isOrderStatusValid(String orderStatus);

    boolean isRepairLevelValid(String repairLevel);

    boolean isWorkDescriptionValid(String workDescription);

    boolean isSparePartsValid(String spareParts);

    boolean isUserRoleValid(String userRole);

    boolean isNewOrderDataValid(NewOrderData newOrderData);

    boolean isOrderDataValid(OrderData orderData);

    boolean isEmployeeValid(EmployeeParameters employeeParameters, String password, String passwordConfirm);
}
