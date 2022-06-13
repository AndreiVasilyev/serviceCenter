package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.exception.DaoException;

import java.util.List;

/**
 * The interface Client dao.
 */
public interface ClientDao extends BaseDao<Client> {

    /**
     * Find by phone list.
     *
     * @param phoneNumber the phone number
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Client> findByPhone(String phoneNumber) throws DaoException;

    /**
     * Create with new address boolean.
     *
     * @param client the client
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createWithNewAddress(Client client) throws DaoException;

    /**
     * Create client long.
     *
     * @param client the client
     * @return the long
     * @throws DaoException the dao exception
     */
    long createClient(Client client) throws DaoException;
}
