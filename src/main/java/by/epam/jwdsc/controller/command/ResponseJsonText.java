package by.epam.jwdsc.controller.command;

public final class ResponseJsonText {

    public static final String POSITIVE_RESPONSE = "ok:";
    public static final String NEGATIVE_RESPONSE = "error:";
    public static final String LOCALE_FILE_NAME = "locale";
    public static final String INVALID_ORDER_NUMBER_EMAIL_LOCAL_KEY = "checkOrder.main.alert.error.invalid.number.or.email";
    public static final String NOMATCH_CODE_EMAIL_LOCAL_KEY = "checkOrder.main.alert.error.code.match.email";
    public static final String INVALID_CODE_EMAIL_LOCAL_KEY = "checkOrder.main.alert.error.invalid.code.or.email";
    public static final String INVALID_ORDER_NUMBER_LOCAL_KEY = "checkOrder.main.alert.error.invalid.order.number";
    public static final String NOT_VERIFIED_CODE_LOCAL_KEY = "checkOrder.main.alert.error.not.verified.code";
    public static final String ORDER_NOT_FOUND_LOCAL_KEY = "checkOrder.main.alert.result.not.found";
    public static final String INVALID_LOGIN_PASSWORD_LOCAL_KEY = "login.main.alert.invalid.login.or.password";
    public static final String NOMATCH_LOGIN_PASSWORD_LOCAL_KEY = "login.main.alert.match.login.or.password";
    public static final String INVALID_LOGIN_LOCAL_KEY = "control.employees.register.invalid.login";
    public static final String DUPLICATE_LOGIN_LOCAL_KEY = "control.employees.register.duplicate.login";
    public static final String UNIQUE_LOGIN_LOCAL_KEY = "control.employees.register.unique.login";
    public static final String ORDER_CREATED_LOCAL_KEY = "control.orders.create.new.order.success";
    public static final String INVALID_ORDER_PARAMETER_LOCAL_KEY = "control.orders.create.new.order.invalid.parameter";
    public static final String SUCCESS_REGISTER_LOCAL_KEY = "control.employees.register.new.employee.success";
    public static final String INVALID_LOGIN_USER_ROLE_LOCAL_KEY = "control.employees.register.invalid.login.or.role";
    public static final String NOT_REGISTERED_LOGIN_LOCAL_KEY = "registration.login.not.registered";
    public static final String INVALID_EMPLOYEE_LOCAL_KEY = "registration.final.invalid.employee.data";
    public static final String PART_CREATED_LOCAL_KEY = "control.parts.create.new.part.success";
    public static final String INVALID_PART_PARAMETER_LOCAL_KEY = "control.parts.create.new.part.invalid.parameter";
    public static final String DEVICE_CREATED_LOCAL_KEY = "control.devices.create.new.device.success";
    public static final String INVALID_DEVICE_PARAMETER_LOCAL_KEY = "control.devices.create.new.device.invalid.parameter";
    public static final String COMPANY_CREATED_LOCAL_KEY = "control.companies.create.new.device.success";
    public static final String INVALID_COMPANY_PARAMETER_LOCAL_KEY = "control.companies.create.new.device.invalid.parameter";
    public static final String INVALID_DEVICE_ID_LOCAL_KEY = "control.prices.find.device.invalid.id.parameter";
    public static final String PRICES_SAVED_LOCAL_KEY = "control.prices.save.prices.success";
    public static final String INVALID_PRICES_PARAMETER_LOCAL_KEY = "control.prices.save.prices.invalid.parameter";

    private ResponseJsonText() {
    }
}
