package by.epam.jwdsc.validator;

/**
 * The type Validator templates.
 */
public final class ValidatorTemplates {

    /**
     * The constant EMAIL_REGEX.
     */
    public static final String EMAIL_REGEX = "^([^\\s@]+@[^\\s@]+\\.[^\\s@]+)?$";
    /**
     * The constant EMAIL_MIN_LENGTH.
     */
    public static final int EMAIL_MIN_LENGTH = 8;
    /**
     * The constant CODE_REGEX.
     */
    public static final String CODE_REGEX = "\\b\\d{5}\\b";
    /**
     * The constant ORDER_NUMBER_REGEX.
     */
    public static final String ORDER_NUMBER_REGEX = "^[PS]\\d{1,5}$";
    /**
     * The constant LOGIN_REGEX.
     */
    public static final String LOGIN_REGEX = "^[\\wа-яА-Я@.]{3,20}$";
    /**
     * The constant PASSWORD_REGEX.
     */
    public static final String PASSWORD_REGEX = "^[\\w^_]{8,20}$";
    /**
     * The constant NEW_ORDER_NUMBER_REGEX.
     */
    public static final String NEW_ORDER_NUMBER_REGEX = "^NO$";
    /**
     * The constant DEVICE_NAME_REGEX.
     */
    public static final String DEVICE_NAME_REGEX = "^([a-zA-Zа-яА-Я]{3,20})([ -][a-zA-Zа-яА-Я]{1,20}){0,4}$";
    /**
     * The constant COMPANY_NAME_REGEX.
     */
    public static final String COMPANY_NAME_REGEX = "^(([a-zA-Zа-яА-Я]{1,20})( [a-zA-Zа-яА-Я]{1,20}){0,3})?$";
    /**
     * The constant ID_REGEX.
     */
    public static final String ID_REGEX = "^(\\d{1,19})?$";
    /**
     * The constant MODEL_REGEX.
     */
    public static final String MODEL_REGEX = "^([\\wа-яА-Я \\-\\+]{3,20})?$";
    /**
     * The constant SERIAL_REGEX.
     */
    public static final String SERIAL_REGEX = "^([\\wа-яА-Я -]{3,20})?$";
    /**
     * The constant FIRST_NAME_REGEX.
     */
    public static final String FIRST_NAME_REGEX = "^[a-zA-ZА-Яа-я-]{3,20}$";
    /**
     * The constant SECOND_NAME_REGEX.
     */
    public static final String SECOND_NAME_REGEX = "^[a-zA-ZА-Яа-я-]{3,40}$";
    /**
     * The constant PATRONYMIC_REGEX.
     */
    public static final String PATRONYMIC_REGEX = "^([a-zA-ZА-Яа-я]{3,20})?$";
    /**
     * The constant PHONE_NUMBER_REGEX.
     */
    public static final String PHONE_NUMBER_REGEX = "^(\\+\\d{12})?$";
    /**
     * The constant COUNTRY_REGEX.
     */
    public static final String COUNTRY_REGEX = "^([a-zA-Zа-яА-Я- ]{3,30})?$";
    /**
     * The constant POSTCODE_REGEX.
     */
    public static final String POSTCODE_REGEX = "^(\\d{6})?$";
    /**
     * The constant STATE_REGEX.
     */
    public static final String STATE_REGEX = "^([a-zA-Zа-яА-Я\\. ]{8,40})?$";
    /**
     * The constant REGION_REGEX.
     */
    public static final String REGION_REGEX = "^([a-zA-Zа-яА-Я\\. -]{8,40})?$";
    /**
     * The constant CITY_REGEX.
     */
    public static final String CITY_REGEX = "^[a-zA-Zа-яА-Я- ]{3,30}$";
    /**
     * The constant STREET_REGEX.
     */
    public static final String STREET_REGEX = "^[a-zA-Zа-яА-Я- \\.]{5,40}$";
    /**
     * The constant HOUSE_NUMBER_REGEX.
     */
    public static final String HOUSE_NUMBER_REGEX = "^[\\d]{1,4}$";
    /**
     * The constant APARTMENT_NUMBER_REGEX.
     */
    public static final String APARTMENT_NUMBER_REGEX = "^([\\d]{1,4})?$";
    /**
     * The constant NOTE_REGEX.
     */
    public static final String NOTE_REGEX = "^([\\wа-яА-Я].*)?$";
    /**
     * The constant DATE_REGEX.
     */
    public static final String DATE_REGEX = "^((\\d{2} ){2}\\d{4} \\d{2}(:\\d{2}){2})?$";
    /**
     * The constant WORK_DESCRIPTION_REGEX.
     */
    public static final String WORK_DESCRIPTION_REGEX = "^([\\wа-яА-Я].{3,200})?$";
    /**
     * The constant SPARE_PARTS_REGEX.
     */
    public static final String SPARE_PARTS_REGEX = "^((\\d{1,5} )+)?$";
    /**
     * The constant PART_NUMBER_REGEX.
     */
    public static final String PART_NUMBER_REGEX = "^([\\wа-яА-Я \\-]{3,30})?$";
    /**
     * The constant PART_NAME_REGEX.
     */
    public static final String PART_NAME_REGEX = "^[\\wа-яА-Я ]{2,40}$";
    /**
     * The constant PART_DESCRIPTION_REGEX.
     */
    public static final String PART_DESCRIPTION_REGEX = "^([\\wа-яА-Я].{1,100})?$";
    /**
     * The constant COST_REGEX.
     */
    public static final String COST_REGEX = "^\\d+(\\.\\d{2})?$";
    /**
     * The constant IS_CONTRACT_REGEX.
     */
    public static final String IS_CONTRACT_REGEX = "^[01]{1}?$";

    private ValidatorTemplates() {
    }
}
