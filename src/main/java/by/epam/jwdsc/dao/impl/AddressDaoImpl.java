package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.AddressDao;
import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AddressDaoImpl implements AddressDao {

    private static final String SQL_SELECT_ALL_ADDRESSES = "SELECT a.address_id, a.country, a.postcode, a.state, " +
            "a.region, a.city, a.street, a.house_number, a.apartment_number FROM addresses AS a";
    private static final String SQL_SELECT_ADDRESS_BY_ID = "SELECT a.address_id, a.country, a.postcode, a.state, " +
            "a.region, a.city, a.street, a.house_number, a.apartment_number FROM addresses AS a WHERE a.address_id=?";
    private static final String SQL_SELECT_ADDRESSES_BY_PARAMS = "SELECT a.address_id, a.country, a.postcode, a.state, " +
            "a.region, a.city, a.street, a.house_number, a.apartment_number FROM addresses AS a WHERE a.country=? AND " +
            "a.postcode=? AND a.state=? AND a.region=? AND a.city=? AND a.street=? AND a.house_number=? AND " +
            "a.apartment_number=?";
    private static final String SQL_DELETE_ADDRESS_BY_ID = "DELETE a FROM addresses AS a WHERE a.address_id=?";
    private static final String SQL_CREATE_ADDRESS = "INSERT INTO addresses(country, postcode, state, region, city, " +
            "street, house_number, apartment_number) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_ADDRESS = "UPDATE addresses AS a SET a.country=?, a.postcode=?, a.state=?, " +
            "a.region=?, a.city=?, a.street=?, a.house_number=?, a.apartment_number=?  WHERE a.address_id=?";


    @Override
    public List<Address> findByParams(Address addressTemplate) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ADDRESSES_BY_PARAMS)) {
            collectAddressQuery(statement, addressTemplate);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Address> addresses = new ArrayList<>();
                while (resultSet.next()) {
                    Address address = extractAddress(resultSet);
                    addresses.add(address);
                }
                return addresses;
            }
        } catch (SQLException e) {
            log.error("Error executing query findByParams from Addresses", e);
            throw new DaoException("Error executing query findByParams from Addresses", e);
        }
    }

    @Override
    public List<Address> findAll() throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ADDRESSES)) {
            List<Address> addresses = new ArrayList<>();
            while (resultSet.next()) {
                Address address = extractAddress(resultSet);
                addresses.add(address);
            }
            return addresses;
        } catch (SQLException e) {
            log.error("Error executing query findAll from Addresses", e);
            throw new DaoException("Error executing query findAll from Addresses", e);
        }
    }

    @Override
    public Optional<Address> findById(long id) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ADDRESS_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<Address> address = Optional.empty();
                if (resultSet.next()) {
                    address = Optional.of(extractAddress(resultSet));
                }
                return address;
            }
        } catch (SQLException e) {
            log.error("Error executing query findById from Addresses", e);
            throw new DaoException("Error executing query findById from Addresses", e);
        }
    }

    @Override
    public boolean delete(Address address) throws DaoException {
        return deleteById(address.getId());
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = false;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ADDRESS_BY_ID)) {
            statement.setLong(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from Addresses", e);
            throw new DaoException("Error executing query deleteById from Addresses", e);
        }
        return result;
    }

    @Override
    public boolean create(Address address) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ADDRESS)) {
            collectAddressQuery(statement, address);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query create new Address", e);
            throw new DaoException("Error executing query create new Address", e);
        }
        return true;
    }

    @Override
    public long createAddress(Address address) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ADDRESS, RETURN_GENERATED_KEYS)) {
            collectAddressQuery(statement, address);
            statement.executeUpdate();
            try (ResultSet generatedAddressKey = statement.getGeneratedKeys()) {
                generatedAddressKey.next();
                return generatedAddressKey.getLong(1);
            }

        } catch (SQLException e) {
            log.error("Error executing query create new Address", e);
            throw new DaoException("Error executing query create new Address", e);
        }
    }

    @Override
    public Optional<Address> update(Address address) throws DaoException {
        Optional<Address> oldAddressFound = findById(address.getId());
        if (oldAddressFound.isPresent()) {
            Address oldSparePart = oldAddressFound.get();
            try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ADDRESS)) {
                collectAddressQuery(statement, address);
                statement.setLong(9, address.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                log.error("Error executing query update Address", e);
                throw new DaoException("Error executing query update Address", e);
            }
        }
        return oldAddressFound;
    }

    private void collectAddressQuery(PreparedStatement statement, Address address) throws SQLException {
        setUnrequitedStringValue(statement, 1, address.getCountry());
        setUnrequitedIntValue(statement, 2, address.getPostcode());
        setUnrequitedStringValue(statement, 3, address.getState());
        setUnrequitedStringValue(statement, 4, address.getRegion());
        statement.setString(5, address.getCity());
        statement.setString(6, address.getStreet());
        statement.setInt(7, address.getHouseNumber());
        setUnrequitedIntValue(statement, 8, address.getApartmentNumber());
    }

    private void setUnrequitedStringValue(PreparedStatement statement, int number, String value) throws SQLException {
        if (value != null && !value.isBlank()) {
            statement.setString(number, value);
        } else {
            statement.setNull(number, Types.VARCHAR);
        }
    }

    private void setUnrequitedIntValue(PreparedStatement statement, int number, int value) throws SQLException {
        if (value != 0) {
            statement.setInt(number, value);
        } else {
            statement.setNull(number, Types.INTEGER);
        }
    }
}
