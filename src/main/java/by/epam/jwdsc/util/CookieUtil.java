package by.epam.jwdsc.util;

import jakarta.servlet.http.Cookie;

import java.util.Arrays;
import java.util.Optional;

/**
 * The type Cookie util.
 */
public final class CookieUtil {

    private static CookieUtil instance;

    private CookieUtil() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CookieUtil getInstance() {
        if (instance == null) {
            instance = new CookieUtil();
        }
        return instance;
    }

    /**
     * Gets cookie.
     *
     * @param cookies    the cookies
     * @param cookieName the cookie name
     * @return the cookie
     */
    public Optional<Cookie> getCookie(Cookie[] cookies, String cookieName) {
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equalsIgnoreCase(cookieName))
                .findFirst();
    }

    /**
     * Remove.
     *
     * @param cookies     the cookies
     * @param cookieName  the cookie name
     * @param cookieValue the cookie value
     */
    public void remove(Cookie[] cookies, String cookieName, String cookieValue) {
        Optional<Cookie> oldCookie = getCookie(cookies, cookieName);
        if (oldCookie.isPresent() && oldCookie.get().getValue().equals(cookieValue)) {
            oldCookie.get().setMaxAge(0);
        }
    }
}
