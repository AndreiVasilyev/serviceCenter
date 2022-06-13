package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.AbstractUser;
import by.epam.jwdsc.entity.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * The type User dao.
 */
public abstract class UserDao {

    /**
     * The constant SQL_CREATE_PHONE_NUMBER.
     */
    protected static final String SQL_CREATE_PHONE_NUMBER = "INSERT INTO phone_numbers(user_id, phone_number) VALUES(?,?)";
    /**
     * The constant SQL_DELETE_PHONE_NUMBER.
     */
    protected static final String SQL_DELETE_PHONE_NUMBER = "DELETE FROM phone_numbers WHERE user_id=?";

    /**
     * Collect create address query.
     *
     * @param <T>       the type parameter
     * @param statement the statement
     * @param user      the user
     * @throws SQLException the sql exception
     */
    protected <T extends AbstractUser> void collectCreateAddressQuery(PreparedStatement statement, T user) throws SQLException {
        Address address = user.getAddress();
        if (address.getCountry() != null && !address.getCountry().isBlank()) {
            statement.setString(1, address.getCountry());
        } else {
            statement.setNull(1, Types.VARCHAR);
        }
        if (address.getPostcode() != 0) {
            statement.setInt(2, address.getPostcode());
        } else {
            statement.setNull(2, Types.INTEGER);
        }
        if (address.getState() != null && !address.getState().isBlank()) {
            statement.setString(3, address.getState());
        } else {
            statement.setNull(3, Types.VARCHAR);
        }
        if (address.getRegion() != null && !address.getRegion().isBlank()) {
            statement.setString(4, address.getRegion());
        } else {
            statement.setNull(4, Types.VARCHAR);
        }
        statement.setString(5, address.getCity());
        statement.setString(6, address.getStreet());
        statement.setInt(7, address.getHouseNumber());
        if (address.getApartmentNumber() != 0) {
            statement.setInt(8, address.getApartmentNumber());
        } else {
            statement.setNull(8, Types.INTEGER);
        }
    }

    /**
     * Collect create user query.
     *
     * @param <T>       the type parameter
     * @param statement the statement
     * @param user      the user
     * @throws SQLException the sql exception
     */
    protected <T extends AbstractUser> void collectCreateUserQuery(PreparedStatement statement, T user) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getSecondName());
        if (user.getPatronymic() != null && !user.getPatronymic().isBlank()) {
            statement.setString(3, user.getPatronymic());
        } else {
            statement.setNull(3, Types.VARCHAR);
        }
        if (user.getAddress() != null) {
            statement.setLong(4, user.getAddress().getId());
        } else {
            statement.setNull(4, Types.BIGINT);
        }
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            statement.setString(5, user.getEmail());
        } else {
            statement.setNull(5, Types.VARCHAR);
        }
        if (user.getUserRole() != null) {
            statement.setString(6, user.getUserRole().name());
        } else {
            statement.setNull(6, Types.VARCHAR);
        }
    }

    /**
     * Update phone numbers.
     *
     * @param <T>        the type parameter
     * @param connection the connection
     * @param user       the user
     * @param oldUser    the old user
     * @throws SQLException the sql exception
     */
    protected <T extends AbstractUser> void updatePhoneNumbers(Connection connection, T user, T oldUser) throws SQLException {
        try (PreparedStatement statementAddPhone = connection.prepareStatement(SQL_CREATE_PHONE_NUMBER);
             PreparedStatement statementDeletePhone = connection.prepareStatement(SQL_DELETE_PHONE_NUMBER)) {
            if (user.getPhones() != null && !user.getPhones().isEmpty()) {
                for (String phoneNumber : user.getPhones()) {
                    if (!oldUser.getPhones().contains(phoneNumber)) {
                        statementAddPhone.setLong(1, user.getId());
                        statementAddPhone.setString(2, phoneNumber);
                        statementAddPhone.executeUpdate();
                    }
                }
                if (!oldUser.getPhones().isEmpty()) {
                    for (String phoneNumber : oldUser.getPhones()) {
                        if (!user.getPhones().contains(phoneNumber)) {
                            statementDeletePhone.setLong(1, user.getId());
                            statementDeletePhone.executeUpdate();
                        }
                    }
                }
            }
        }
    }
}
