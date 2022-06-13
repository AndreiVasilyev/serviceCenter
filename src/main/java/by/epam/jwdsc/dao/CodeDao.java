package by.epam.jwdsc.dao;

import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Code dao.
 */
public interface CodeDao {

    /**
     * The constant log.
     */
    Logger log = LogManager.getLogger();

    /**
     * Find all map.
     *
     * @return the map
     * @throws DaoException the dao exception
     */
    Map<String, String> findAll() throws DaoException;

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<String> findByEmail(String email) throws DaoException;

    /**
     * Delete by code boolean.
     *
     * @param code the code
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteByCode(String code) throws DaoException;

    /**
     * Delete by email boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteByEmail(String email) throws DaoException;

    /**
     * Create boolean.
     *
     * @param email the email
     * @param code  the code
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean create(String email, String code) throws DaoException;

    /**
     * Update string.
     *
     * @param email the email
     * @param code  the code
     * @return the string
     * @throws DaoException the dao exception
     */
    String update(String email, String code) throws DaoException;

    /**
     * Close.
     *
     * @param statement the statement
     */
    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            log.error("Error when closing statement", e);
        }
    }

    /**
     * Close.
     *
     * @param connection the connection
     */
    default void close(Connection connection) {
        if (connection != null) {
            DbConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

}
