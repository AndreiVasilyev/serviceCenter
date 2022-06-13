package by.epam.jwdsc.entity;

import java.util.List;

/**
 * The interface User builder.
 *
 * @param <S> the type parameter
 * @param <T> the type parameter
 */
public interface UserBuilder<S extends UserBuilder, T> {

    /**
     * Id s.
     *
     * @param id the id
     * @return the s
     */
    S id(long id);

    /**
     * First name s.
     *
     * @param firstName the first name
     * @return the s
     */
    S firstName(String firstName);

    /**
     * Second name s.
     *
     * @param secondName the second name
     * @return the s
     */
    S secondName(String secondName);

    /**
     * Patronymic s.
     *
     * @param patronymic the patronymic
     * @return the s
     */
    S patronymic(String patronymic);

    /**
     * Address s.
     *
     * @param address the address
     * @return the s
     */
    S address(Address address);

    /**
     * Phones s.
     *
     * @param phones the phones
     * @return the s
     */
    S phones(List<String> phones);

    /**
     * Email s.
     *
     * @param email the email
     * @return the s
     */
    S email(String email);

    /**
     * User role s.
     *
     * @param email the email
     * @return the s
     */
    S userRole(UserRole email);

    /**
     * Build t.
     *
     * @return the t
     */
    T build();
}
