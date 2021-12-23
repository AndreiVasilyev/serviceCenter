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

public interface BaseDao<T extends CommonEntity> {
    Logger log = LogManager.getLogger();

    List<T> findAll() throws DaoException;

    T findById(long id);

    boolean delete(T t);

    boolean deleteById(long id);

    boolean create(T t);

    T update(T t);

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
