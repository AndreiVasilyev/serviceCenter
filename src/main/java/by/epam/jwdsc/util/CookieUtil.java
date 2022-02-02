package by.epam.jwdsc.util;

import jakarta.servlet.http.Cookie;

import java.util.Arrays;
import java.util.Optional;

public final class CookieUtil {

    private static CookieUtil instance;

    private CookieUtil() {
    }

    public static CookieUtil getInstance() {
        if (instance == null) {
            instance = new CookieUtil();
        }
        return instance;
    }

    public Optional<Cookie> getCookie(Cookie[] cookies, String cookieName) {
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equalsIgnoreCase(cookieName))
                .findFirst();
    }

    public void remove(Cookie[] cookies, String cookieName, String cookieValue) {
        Optional<Cookie> oldCookie = getCookie(cookies, cookieName);
        if (oldCookie.isPresent() && oldCookie.get().getValue().equals(cookieValue)) {
            oldCookie.get().setMaxAge(0);
        }
    }
}
