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

public interface CodeDao {

    Logger log = LogManager.getLogger();

    Map<String, String> findAll() throws DaoException;

    Optional<String> findByEmail(String email) throws DaoException;

    boolean deleteByCode(String code) throws DaoException;

    boolean deleteByEmail(String email) throws DaoException;

    boolean create(String email, String code) throws DaoException;

    String update(String email, String code) throws DaoException;

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            log.error("Error when closing statement", e);
        }
    }

    default void close(Connection connection) {
        if (connection != null) {
            DbConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

}
