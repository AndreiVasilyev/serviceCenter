package by.epam.jwdsc.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The enum Db connection pool.
 */
public enum DbConnectionPool {
    /**
     * Instance db connection pool.
     */
    INSTANCE;

    private static final Logger log = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 8;
    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> workingConnections;

    DbConnectionPool() {
        this.availableConnections = new LinkedBlockingDeque<>();
        this.workingConnections = new LinkedBlockingDeque<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                availableConnections.offer(ProxyConnectionFactory.getProxyConnection());
            } catch (SQLException e) {
                LogManager.getLogger().error("Error when creating new proxyConnection in {} iteration", i, e);
            }
        }
        if (availableConnections.isEmpty()) {
            LogManager.getLogger().fatal("Fatal error, can't create any connection");
            throw new RuntimeException("Fatal error, can't create any connection");
        } else if (availableConnections.size() < DEFAULT_POOL_SIZE) {
            for (int i = 0; i < DEFAULT_POOL_SIZE - availableConnections.size(); i++) {
                try {
                    availableConnections.offer(ProxyConnectionFactory.getProxyConnection());
                } catch (SQLException e) {
                    LogManager.getLogger().fatal("Error when re-creating new proxyConnection in {} iteration", i, e);
                    throw new RuntimeException("Fatal error, can't create enough connections", e);
                }
            }
        }
        LogManager.getLogger().info("DbConnectionPool created");
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = availableConnections.take();
            workingConnections.offer(connection);
            log.debug("Connection #{} given", (availableConnections.size() - DEFAULT_POOL_SIZE));
        } catch (InterruptedException e) {
            log.error("Interrupted exception when trying get connection", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Release connection boolean.
     *
     * @param connection the connection
     * @return the boolean
     */
    public boolean releaseConnection(Connection connection) {
        boolean isRemoved = false;
        if (connection instanceof ProxyConnection proxyConnection) {
            try {
                isRemoved = workingConnections.remove(proxyConnection);
                if (isRemoved) {
                    availableConnections.put(proxyConnection);
                } else {
                    log.error("Trying release not native connection. Connection not released");
                }
            } catch (InterruptedException e) {
                log.error("Interrupted exception when trying release connection", e);
                Thread.currentThread().interrupt();
            }
        } else {
            log.error("Trying release not ProxyConnection connection. Connection not released");
        }
        return isRemoved;
    }

    /**
     * Destroy pool.
     */
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                availableConnections.take().realClose();
            } catch (InterruptedException e) {
                log.error("Interrupted exception when trying destroy connection (iteration {})", i, e);
                Thread.currentThread().interrupt();
            } catch (SQLException e) {
                log.error("SQL exception when trying destroy connection (iteration {})", i, e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                log.error("SQL exception when trying deregister drivers", e);
            }
        });
    }
}
