package by.epam.jwdsc.controller.listener;


import by.epam.jwdsc.entity.OrderStatus;
import by.epam.jwdsc.entity.RepairLevel;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.Locale;

import static by.epam.jwdsc.controller.command.SessionAttribute.*;

/**
 * The type Session listener.
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(LOCALE, Locale.getDefault());
        session.setAttribute(REPAIR_LEVEL, RepairLevel.values());
        session.setAttribute(ORDER_STATUS, OrderStatus.values());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.invalidate();
    }
}
