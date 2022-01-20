package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.SparePartDao;
import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.jwdsc.dao.ColumnName.*;

public class SparePartDaoImpl implements SparePartDao {

    private static final String SQL_SELECT_ALL_SPARE_PARTS = "SELECT s.id, s.part_number, s.name, s.description, " +
            "s.cost FROM spare_parts AS s";
    private static final String SQL_SELECT_SPARE_PART_BY_ID = "SELECT s.id, s.part_number, s.name, s.description, " +
            "s.cost FROM spare_parts AS s WHERE s.id=?";
    private static final String SQL_DELETE_SPARE_PART_BY_ID = "DELETE s FROM spare_parts AS s WHERE s.id=?";
    private static final String SQL_CREATE_SPARE_PART = "INSERT INTO spare_parts(part_number, name, description, cost) " +
            "VALUES(?,?,?,?)";
    private static final String SQL_UPDATE_SPARE_PART = "UPDATE spare_parts AS s SET s.part_number=?, s.name=?, " +
            "s.description=?, s.cost=? WHERE s.id=?";

    @Override
    public List<SparePart> findAll() throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_SPARE_PARTS)) {
            return extractSpareParts(resultSet);
        } catch (SQLException e) {
            log.error("Error executing query findAll from SpareParts", e);
            throw new DaoException("Error executing query findAll from SpareParts", e);
        }
    }

    @Override
    public Optional<SparePart> findById(long id) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SPARE_PART_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<SparePart> spareParts = extractSpareParts(resultSet);
                return Optional.ofNullable(spareParts.get(0));
            }
        } catch (SQLException e) {
            log.error("Error executing query findById from SpareParts", e);
            throw new DaoException("Error executing query findById from SpareParts", e);
        }

    }

    @Override
    public boolean delete(SparePart sparePart) throws DaoException {
        return deleteById(sparePart.getId());
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = false;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SPARE_PART_BY_ID)) {
            statement.setLong(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from SpareParts", e);
            throw new DaoException("Error executing query deleteById from SpareParts", e);
        }
        return result;
    }

    @Override
    public boolean create(SparePart sparePart) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_SPARE_PART)) {
            collectCreateSparePartQuery(statement, sparePart);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query create new SparePart", e);
            throw new DaoException("Error executing query create new SparePart", e);
        }
        return true;
    }

    @Override
    public Optional<SparePart> update(SparePart sparePart) throws DaoException {
        Optional<SparePart> oldSparePartFound = findById(sparePart.getId());
        if (oldSparePartFound.isPresent()) {
            SparePart oldSparePart = oldSparePartFound.get();
            try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SPARE_PART)) {
                collectUpdateSparePartQuery(statement, sparePart);
                statement.executeUpdate();
            } catch (SQLException e) {
                log.error("Error executing query update SparePart", e);
                throw new DaoException("Error executing query update SparePart", e);
            }
        }
        return oldSparePartFound;
    }

    private void collectCreateSparePartQuery(PreparedStatement statement, SparePart sparePart) throws SQLException {
        if (sparePart.getPartNumber() != null && !sparePart.getPartNumber().isBlank()) {
            statement.setString(1, sparePart.getPartNumber());
        } else {
            statement.setNull(1, Types.VARCHAR);
        }
        statement.setString(2, sparePart.getName());
        if (sparePart.getDescription() != null && !sparePart.getDescription().isBlank()) {
            statement.setString(3, sparePart.getDescription());
        } else {
            statement.setNull(3, Types.VARCHAR);
        }
        statement.setBigDecimal(4, sparePart.getCost());
    }

    private void collectUpdateSparePartQuery(PreparedStatement statement, SparePart sparePart) throws SQLException {
        statement.setString(1, sparePart.getPartNumber());
        statement.setString(2, sparePart.getName());
        statement.setString(3, sparePart.getDescription());
        statement.setBigDecimal(4, sparePart.getCost());
        statement.setLong(5, sparePart.getId());
    }
}
