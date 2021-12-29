package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.CommonEntity;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends CommonEntity> {
    Logger log = LogManager.getLogger();

    List<T> findAll() throws DaoException;

    Optional<T> findById(long id) throws DaoException;

    boolean delete(T t) throws DaoException;

    boolean deleteById(long id) throws DaoException;

    boolean create(T t) throws DaoException;

    T update(T t) throws DaoException;

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
