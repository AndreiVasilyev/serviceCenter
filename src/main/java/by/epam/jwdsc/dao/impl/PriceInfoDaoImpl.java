package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.PriceInfoDao;
import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.entity.PriceInfo;
import by.epam.jwdsc.entity.RepairLevel;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.jwdsc.dao.ColumnName.*;

public class PriceInfoDaoImpl implements PriceInfoDao {

    private static final String SQL_SELECT_ALL_PRICES = "SELECT p.id, p.device_id, p.repair_level, p.repair_cost, " +
            "d.device_name FROM prices AS p JOIN devices AS d USING (device_id)";
    private static final String SQL_SELECT_PRICE_BY_ID = "SELECT p.id, p.device_id, p.repair_level, p.repair_cost, " +
            "d.device_name FROM prices AS p JOIN devices AS d USING (device_id) WHERE p.id=?";
    private static final String SQL_DELETE_PRICE_BY_ID = "DELETE p FROM prices AS p WHERE p.id=?";
    private static final String SQL_CREATE_PRICE = "INSERT INTO prices(device_id, repair_level, repair_cost) " +
            "VALUES(?,?,?)";
    private static final String SQL_UPDATE_PRICE = "UPDATE prices AS p SET p.device_id=?, p.repair_level=?, " +
            "p.repair_cost=? WHERE p.id=?";

    @Override
    public List<PriceInfo> findAll() throws DaoException {
        List<PriceInfo> prices = new ArrayList<>();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PRICES)) {
            while (resultSet.next()) {
                PriceInfo price = extractPrice(resultSet);
                prices.add(price);
            }
        } catch (SQLException e) {
            log.error("Error executing query findAll from Prices", e);
            throw new DaoException("Error executing query findAll from Prices", e);
        } finally {
            close(connection);
        }
        return prices;
    }

    @Override
    public Optional<PriceInfo> findById(long id) throws DaoException {
        Optional<PriceInfo> price = Optional.empty();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PRICE_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    price = Optional.of(extractPrice(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error("Error executing query findById from Prices", e);
            throw new DaoException("Error executing query findById from Prices", e);
        } finally {
            close(connection);
        }
        return price;
    }

    @Override
    public boolean delete(PriceInfo priceInfo) throws DaoException {
        return deleteById(priceInfo.getId());
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = false;
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PRICE_BY_ID)) {
            statement.setLong(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from Prices", e);
            throw new DaoException("Error executing query deleteById from Prices", e);
        } finally {
            close(connection);
        }
        return result;
    }

    @Override
    public boolean create(PriceInfo priceInfo) throws DaoException {
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_PRICE)) {
            collectCreatePriceQuery(statement, priceInfo);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query create new Price", e);
            throw new DaoException("Error executing query create new Price", e);
        } finally {
            close(connection);
        }
        return true;
    }

    @Override
    public PriceInfo update(PriceInfo priceInfo) throws DaoException {
        PriceInfo oldPrice = findById(priceInfo.getId()).get();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PRICE)) {
            collectUpdatePriceQuery(statement, priceInfo);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query update Price", e);
            throw new DaoException("Error executing query update Price", e);
        } finally {
            close(connection);
        }
        return oldPrice;
    }

    private PriceInfo extractPrice(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(PRICES_ID);
        long deviceId = resultSet.getLong(PRICES_DEVICE_ID);
        String repairLevelValue = resultSet.getString(PRICES_REPAIR_LEVEL);
        RepairLevel repairLevel = RepairLevel.valueOf(repairLevelValue);
        BigDecimal repairCost = resultSet.getBigDecimal(PRICES_REPAIR_COST);
        return new PriceInfo(id, deviceId, repairLevel, repairCost);
    }

    private void collectCreatePriceQuery(PreparedStatement statement, PriceInfo priceInfo) throws SQLException {
        statement.setLong(1, priceInfo.getDevice());
        statement.setString(2, priceInfo.getRepairLevel().name());
        statement.setBigDecimal(3, priceInfo.getRepairCost());
    }

    private void collectUpdatePriceQuery(PreparedStatement statement, PriceInfo priceInfo) throws SQLException {
        collectCreatePriceQuery(statement, priceInfo);
        statement.setLong(4, priceInfo.getId());
    }
}
