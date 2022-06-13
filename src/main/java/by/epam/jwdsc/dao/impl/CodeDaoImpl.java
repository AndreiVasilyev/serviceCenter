package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.CodeDao;
import by.epam.jwdsc.entity.PriceInfo;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.sql.*;
import java.util.*;

import static by.epam.jwdsc.dao.ColumnName.*;

/**
 * The type Code dao.
 */
public class CodeDaoImpl implements CodeDao {

    private static final String SQL_SELECT_ALL_CODES = "SELECT c.email, c.code FROM codes AS c";
    private static final String SQL_SELECT_CODE_BY_EMAIL = "SELECT c.email, c.code FROM codes AS c WHERE c.email=?";
    private static final String SQL_DELETE_CODE_BY_EMAIL = "DELETE c FROM codes AS c WHERE c.email=?";
    private static final String SQL_DELETE_EMAIL_BY_CODE = "DELETE c FROM codes AS c WHERE c.code=?";
    private static final String SQL_CREATE_CODE = "INSERT INTO codes(email, code) VALUES(?,?)";
    private static final String SQL_UPDATE_CODE = "UPDATE codes AS c SET c.code=? WHERE c.email=?";


    @Override
    public Map<String, String> findAll() throws DaoException {
        Map<String, String> codes = new HashMap<>();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CODES)) {
            while (resultSet.next()) {
                String email = resultSet.getString(CODES_EMAIL);
                String code = resultSet.getString(CODES_CODE);
                codes.put(email, code);
            }
        } catch (SQLException e) {
            log.error("Error executing query findAll from Codes", e);
            throw new DaoException("Error executing query findAll from Codes", e);
        }
        return codes;
    }

    @Override
    public Optional<String> findByEmail(String email) throws DaoException {
        Optional<String> code = Optional.empty();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_CODE_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    code = Optional.of(resultSet.getString(CODES_CODE));
                }
            }
        } catch (SQLException e) {
            log.error("Error executing query findByEmail from Codes", e);
            throw new DaoException("Error executing query findByEmail from Codes", e);
        }
        return code;
    }

    @Override
    public boolean deleteByCode(String code) throws DaoException {
        boolean result = false;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_EMAIL_BY_CODE)) {
            statement.setString(1, code);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteByCode from Codes", e);
            throw new DaoException("Error executing query deleteByCode from Codes", e);
        }
        return result;
    }

    @Override
    public boolean deleteByEmail(String email) throws DaoException {
        boolean result = false;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CODE_BY_EMAIL)) {
            statement.setString(1, email);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteByEmail from Codes", e);
            throw new DaoException("Error executing query deleteByEmail from Codes", e);
        }
        return result;
    }

    @Override
    public boolean create(String email, String code) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_CODE)) {
            statement.setString(1, email);
            statement.setString(2, code);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query create new Code", e);
            throw new DaoException("Error executing query create new Code", e);
        }
        return true;
    }

    @Override
    public String update(String email, String code) throws DaoException {
        String oldCode = findByEmail(email).get();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CODE)) {
            statement.setString(1, code);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query update Code", e);
            throw new DaoException("Error executing query update Code", e);
        }
        return oldCode;
    }
}
