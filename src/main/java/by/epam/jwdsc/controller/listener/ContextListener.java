package by.epam.jwdsc.controller.listener;

import by.epam.jwdsc.pool.DbConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * The type Context listener.
 */
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbConnectionPool.INSTANCE.getConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DbConnectionPool.INSTANCE.destroyPool();
    }
}
