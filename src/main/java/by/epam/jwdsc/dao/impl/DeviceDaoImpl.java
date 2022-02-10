package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.DeviceDao;
import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.jwdsc.dao.ColumnName.*;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class DeviceDaoImpl implements DeviceDao {

    private static final String SQL_SELECT_ALL_DEVICES = "SELECT d.device_id, d.device_name FROM devices AS d";
    private static final String SQL_SELECT_DEVICE_BY_ID = "SELECT d.device_id, d.device_name FROM devices AS d " +
            "WHERE d.device_id=?";
    private static final String SQL_DELETE_DEVICE_BY_ID = "DELETE d FROM devices AS d WHERE d.device_id=?";
    private static final String SQL_CREATE_DEVICE = "INSERT INTO devices(device_name) VALUES(?)";
    private static final String SQL_UPDATE_DEVICE = "UPDATE devices AS d SET d.device_name=? WHERE d.device_id=?";

    @Override
    public List<Device> findAll() throws DaoException {
        List<Device> devices = new ArrayList<>();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_DEVICES)) {
            while (resultSet.next()) {
                Device device = extractDevice(resultSet);
                devices.add(device);
            }
        } catch (SQLException e) {
            log.error("Error executing query findAll from Devices", e);
            throw new DaoException("Error executing query findAll from Devices", e);
        }
        return devices;
    }

    @Override
    public Optional<Device> findById(long id) throws DaoException {
        Optional<Device> device = Optional.empty();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_DEVICE_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    device = Optional.of(extractDevice(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error("Error executing query findById from Devices", e);
            throw new DaoException("Error executing query findById from Devices", e);
        }
        return device;
    }

    @Override
    public boolean delete(Device device) throws DaoException {
        return deleteById(device.getId());
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = false;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DEVICE_BY_ID)) {
            statement.setLong(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from Devices", e);
            throw new DaoException("Error executing query deleteById from Devices", e);
        }
        return result;
    }

    @Override
    public boolean create(Device device) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_DEVICE)) {
            statement.setString(1, device.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query create new Device", e);
            throw new DaoException("Error executing query create new Device", e);
        }
        return true;
    }

    @Override
    public long createDevice(Device device) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_DEVICE, RETURN_GENERATED_KEYS)) {
            statement.setString(1, device.getName());
            statement.executeUpdate();
            try (ResultSet generatedDeviceKey = statement.getGeneratedKeys()) {
                generatedDeviceKey.next();
                return generatedDeviceKey.getLong(1);
            }
        } catch (SQLException e) {
            log.error("Error executing query create new Device", e);
            throw new DaoException("Error executing query create new Device", e);
        }
    }

    @Override
    public Optional<Device> update(Device device) throws DaoException {
        Optional<Device> oldDeviceFound = findById(device.getId());
        if (oldDeviceFound.isPresent()) {
            try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DEVICE)) {
                statement.setString(1, device.getName());
                statement.setLong(2, device.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                log.error("Error executing query update Device", e);
                throw new DaoException("Error executing query update Device", e);
            }
        }
        return oldDeviceFound;
    }

    private Device extractDevice(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(DEVICES_ID);
        String name = resultSet.getString(DEVICES_NAME);
        return new Device(id, name);
    }

}
