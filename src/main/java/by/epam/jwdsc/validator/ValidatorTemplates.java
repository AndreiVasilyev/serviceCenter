package by.epam.jwdsc.validator;

public final class ValidatorTemplates {

    public static final String EMAIL_REGEX = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
    public static final int EMAIL_MIN_LENGTH = 8;
    public static final String CODE_REGEX = "\\b\\d{5}\\b";
    public static final String ORDER_NUMBER_REGEX = "^[PS]\\d{1,5}$";
    public static final String LOGIN_REGEX = "^[\\wа-яА-Я@.]{3,20}$";
    public static final String PASSWORD_REGEX = "^[\\w^_]{8,20}$";
    public static final String NEW_ORDER_NUMBER_REGEX = "^NO$";
    public static final String DEVICE_NAME_REGEX = "^([a-zA-zа-яА-Я]{3,20})( [a-zA-zа-яА-Я]{1,20}){0,4}$";
    public static final String COMPANY_NAME_REGEX = "^(([a-zA-zа-яА-Я]{3,20})( [a-zA-zа-яА-Я]{1,20}){0,2})?$";
    public static final String ID_REGEX = "^(\\d{1,19})?$";
    public static final String MODEL_REGEX = "^([\\wа-яА-Я -]{3,20})?$";
    public static final String SERIAL_REGEX = "^([\\wа-яА-Я -]{3,20})?$";
    public static final String FIRST_NAME_REGEX = "^[a-zA-ZА-Яа-я-]{3,20}$";
    public static final String SECOND_NAME_REGEX = "^[a-zA-ZА-Яа-я-]{3,40}$";
    public static final String PATRONYMIC_REGEX = "^([a-zA-ZА-Яа-я]{3,20})?$";
    public static final String PHONE_NUMBER_REGEX = "^\\+\\d{12}$";
    public static final String COUNTRY_REGEX = "^([a-zA-Zа-яА-Я- ]{3,30})?$";
    public static final String POSTCODE_REGEX = "^(\\d{6})?$";
    public static final String STATE_REGEX = "^([a-zA-Zа-яА-Я\\. ]{8,40})?$";
    public static final String REGION_REGEX = "^([a-zA-Zа-яА-Я\\. -]{8,40})?$";
    public static final String CITY_REGEX = "^[a-zA-Zа-яА-Я- ]{3,30}$";
    public static final String STREET_REGEX = "^[a-zA-Zа-яА-Я- \\.]{5,40}$";
    public static final String HOUSE_NUMBER_REGEX = "^[\\d]{1,4}$";
    public static final String APARTMENT_NUMBER_REGEX = "^([\\d]{1,4})?$";
    public static final String NOTE_REGEX = "^([\\wа-яА-Я].*)?$";

    private ValidatorTemplates() {
    }
}
