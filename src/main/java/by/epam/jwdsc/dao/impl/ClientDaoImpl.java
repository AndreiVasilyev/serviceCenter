package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.dao.ClientDao;
import by.epam.jwdsc.entity.CommonEntity;
import by.epam.jwdsc.entity.UserBuilders;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.jwdsc.dao.ColumnName.*;

public class ClientDaoImpl implements ClientDao {

    private static final String SQL_SELECT_ALL_CLIENTS = "SELECT c.user_id, c.discount, u.first_name, u.second_name, " +
            "u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, a.region, a.city, a.street, " +
            "a.house_number, a.apartment_number FROM clients AS c JOIN users AS u USING (user_id) " +
            "JOIN addresses AS a ON (u.address=a.address_id)";


    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DbConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLIENTS);
            while (resultSet.next()) {
                Client client = extractClient(resultSet);
                clients.add(client);
            }
        } catch (SQLException e) {
            log.error("", e);//FIXME descr
            throw new DaoException("", e);
        } finally {
            close(statement);
            close(connection);
        }
        return null;
    }

    @Override
    public CommonEntity findById(long id) {
        return null;
    }

    @Override
    public boolean delete(CommonEntity commonEntity) {
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public boolean create(CommonEntity commonEntity) {
        return false;
    }

    @Override
    public CommonEntity update(CommonEntity commonEntity) {
        return null;
    }

    private Client extractClient(ResultSet resultSet) throws SQLException {
        Address address = extractAddress(resultSet);
        long id = resultSet.getInt(USERS_ID);
        String firstName = resultSet.getString(USERS_FIRST_NAME);
        String secondName = resultSet.getString(USERS_SECOND_NAME);
        String patronymic = resultSet.getString(USERS_PATRONYMIC);
        String email = resultSet.getString(USERS_EMAIL);
        int discount = resultSet.getInt(CLIENTS_DISCOUNT);
        Client client = UserBuilders.newClient()
                .id(id)
                .firstName(firstName)
                .secondName(secondName)
                .patronymic(patronymic)
                .address(address)
                .email(email)
                .discount(discount)
                .build();
        return client;
    }

    private Address extractAddress(ResultSet resultSet) throws SQLException {
        long addressId = resultSet.getLong(ADDRESSES_ID);
        String country = resultSet.getString(ADDRESSES_COUNTRY);
        int postcode = resultSet.getInt(ADDRESSES_POSTCODE);
        String state = resultSet.getString(ADDRESSES_STATE);
        String region = resultSet.getString(ADDRESSES_REGION);
        String city = resultSet.getString(ADDRESSES_CITY);
        String street = resultSet.getString(ADDRESSES_STREET);
        int houseNumber = resultSet.getInt(ADDRESSES_HOUSE_NUMBER);
        int apartmentNumber = resultSet.getInt(ADDRESSES_APARTMENT_NUMBER);
        Address address = new Address.Builder(addressId, city, street, houseNumber)
                .country(country)
                .postcode(postcode)
                .state(state)
                .region(region)
                .apartmentNumber(apartmentNumber)
                .build();
        return address;
    }
}
