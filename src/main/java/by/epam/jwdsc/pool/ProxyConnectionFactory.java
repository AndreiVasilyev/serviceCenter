package by.epam.jwdsc.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

class ProxyConnectionFactory {

    private static final Logger log = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String PROPERTIES_FILE_NAME = "db";
    private static final String DB_URL_PROPERTY_NAME = "db.url";
    private static final String DB_DRIVER_PROPERTY_NAME = "db.driver";
    private static final String DB_USER_PROPERTY_NAME = "db.user";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";
    private static final String DB_URL;
    private static final String DB_DRIVER;
    private static final String DB_USER;
    private static final String DB_PASSWORD;

    static {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE_NAME);
            DB_URL = resourceBundle.getString(DB_URL_PROPERTY_NAME);
            DB_DRIVER = resourceBundle.getString(DB_DRIVER_PROPERTY_NAME);
            DB_USER = resourceBundle.getString(DB_USER_PROPERTY_NAME);
            DB_PASSWORD = resourceBundle.getString(DB_PASSWORD_PROPERTY_NAME);
            Class.forName(DB_DRIVER);
        } catch (MissingResourceException e) {
            log.fatal("Resources bundle {} or any property not found", PROPERTIES_FILE_NAME, e);
            throw new RuntimeException("Resources bundle or any property not found", e);
        } catch (ClassCastException e) {
            log.fatal("Can't convert DB property from resource bundle {} to String", PROPERTIES_FILE_NAME, e);
            throw new RuntimeException("Can't convert DB property from resource bundle {} to String", e);
        } catch (ClassNotFoundException e) {
            log.fatal("DB driver not found", e);
            throw new RuntimeException("DB driver not found", e);
        }
    }

    private ProxyConnectionFactory() {
    }

    static ProxyConnection getProxyConnection() throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD));
    }

}
