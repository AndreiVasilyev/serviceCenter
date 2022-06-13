package by.epam.jwdsc.validator;

import by.epam.jwdsc.entity.dto.*;

/**
 * The interface Validator.
 */
public interface Validator {
    /**
     * Is email valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isEmailValid(String email);

    /**
     * Is unrequited email valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isUnrequitedEmailValid(String email);

    /**
     * Is order number valid boolean.
     *
     * @param orderNumber the order number
     * @return the boolean
     */
    boolean isOrderNumberValid(String orderNumber);

    /**
     * Is code valid boolean.
     *
     * @param code the code
     * @return the boolean
     */
    boolean isCodeValid(String code);

    /**
     * Is login valid boolean.
     *
     * @param code the code
     * @return the boolean
     */
    boolean isLoginValid(String code);

    /**
     * Is password valid boolean.
     *
     * @param password the password
     * @return the boolean
     */
    boolean isPasswordValid(String password);

    /**
     * Is new order number valid boolean.
     *
     * @param orderNumber the order number
     * @return the boolean
     */
    boolean isNewOrderNumberValid(String orderNumber);

    /**
     * Is device name valid boolean.
     *
     * @param deviceName the device name
     * @return the boolean
     */
    boolean isDeviceNameValid(String deviceName);

    /**
     * Is company name valid boolean.
     *
     * @param companyName the company name
     * @return the boolean
     */
    boolean isCompanyNameValid(String companyName);

    /**
     * Is id valid boolean.
     *
     * @param companyName the company name
     * @return the boolean
     */
    boolean isIdValid(String companyName);

    /**
     * Is model valid boolean.
     *
     * @param model the model
     * @return the boolean
     */
    boolean isModelValid(String model);

    /**
     * Is serial valid boolean.
     *
     * @param serial the serial
     * @return the boolean
     */
    boolean isSerialValid(String serial);

    /**
     * Is first name valid boolean.
     *
     * @param firstName the first name
     * @return the boolean
     */
    boolean isFirstNameValid(String firstName);

    /**
     * Is second name valid boolean.
     *
     * @param secondName the second name
     * @return the boolean
     */
    boolean isSecondNameValid(String secondName);

    /**
     * Is patronymic valid boolean.
     *
     * @param patronymic the patronymic
     * @return the boolean
     */
    boolean isPatronymicValid(String patronymic);

    /**
     * Is phone number valid boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    boolean isPhoneNumberValid(String phoneNumber);

    /**
     * Is required phone number valid boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    boolean isRequiredPhoneNumberValid(String phoneNumber);

    /**
     * Is country valid boolean.
     *
     * @param country the country
     * @return the boolean
     */
    boolean isCountryValid(String country);

    /**
     * Is postcode valid boolean.
     *
     * @param postcode the postcode
     * @return the boolean
     */
    boolean isPostcodeValid(String postcode);

    /**
     * Is state valid boolean.
     *
     * @param state the state
     * @return the boolean
     */
    boolean isStateValid(String state);

    /**
     * Is region valid boolean.
     *
     * @param region the region
     * @return the boolean
     */
    boolean isRegionValid(String region);

    /**
     * Is city valid boolean.
     *
     * @param city the city
     * @return the boolean
     */
    boolean isCityValid(String city);

    /**
     * Is street valid boolean.
     *
     * @param street the street
     * @return the boolean
     */
    boolean isStreetValid(String street);

    /**
     * Is house number valid boolean.
     *
     * @param houseNumber the house number
     * @return the boolean
     */
    boolean isHouseNumberValid(String houseNumber);

    /**
     * Is apartment number valid boolean.
     *
     * @param apartmentNumber the apartment number
     * @return the boolean
     */
    boolean isApartmentNumberValid(String apartmentNumber);

    /**
     * Is note valid boolean.
     *
     * @param note the note
     * @return the boolean
     */
    boolean isNoteValid(String note);

    /**
     * Is date valid boolean.
     *
     * @param date the date
     * @return the boolean
     */
    boolean isDateValid(String date);

    /**
     * Is order status valid boolean.
     *
     * @param orderStatus the order status
     * @return the boolean
     */
    boolean isOrderStatusValid(String orderStatus);

    /**
     * Is repair level valid boolean.
     *
     * @param repairLevel the repair level
     * @return the boolean
     */
    boolean isRepairLevelValid(String repairLevel);

    /**
     * Is work description valid boolean.
     *
     * @param workDescription the work description
     * @return the boolean
     */
    boolean isWorkDescriptionValid(String workDescription);

    /**
     * Is spare parts valid boolean.
     *
     * @param spareParts the spare parts
     * @return the boolean
     */
    boolean isSparePartsValid(String spareParts);

    /**
     * Is user role valid boolean.
     *
     * @param userRole the user role
     * @return the boolean
     */
    boolean isUserRoleValid(String userRole);

    /**
     * Is part number valid boolean.
     *
     * @param partNumber the part number
     * @return the boolean
     */
    boolean isPartNumberValid(String partNumber);

    /**
     * Is part name valid boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isPartNameValid(String name);

    /**
     * Is part description valid boolean.
     *
     * @param description the description
     * @return the boolean
     */
    boolean isPartDescriptionValid(String description);

    /**
     * Is cost valid boolean.
     *
     * @param cost the cost
     * @return the boolean
     */
    boolean isCostValid(String cost);

    /**
     * Is contract valid boolean.
     *
     * @param isContract the is contract
     * @return the boolean
     */
    boolean isContractValid(String isContract);

    /**
     * Is new order data valid boolean.
     *
     * @param newOrderData the new order data
     * @return the boolean
     */
    boolean isNewOrderDataValid(NewOrderData newOrderData);

    /**
     * Is order data valid boolean.
     *
     * @param orderData the order data
     * @return the boolean
     */
    boolean isOrderDataValid(OrderData orderData);

    /**
     * Is employee valid boolean.
     *
     * @param employeeParameters the employee parameters
     * @param password           the password
     * @param passwordConfirm    the password confirm
     * @return the boolean
     */
    boolean isEmployeeValid(EmployeeParameters employeeParameters, String password, String passwordConfirm);

    /**
     * Is part valid boolean.
     *
     * @param sparePart the spare part
     * @return the boolean
     */
    boolean isPartValid(SparePartData sparePart);

    /**
     * Is prices data valid boolean.
     *
     * @param pricesData the prices data
     * @return the boolean
     */
    boolean isPricesDataValid(PricesData pricesData);
}
