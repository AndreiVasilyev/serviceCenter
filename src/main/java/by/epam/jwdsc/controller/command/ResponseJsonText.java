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

    private ResponseJsonText() {
    }
}
