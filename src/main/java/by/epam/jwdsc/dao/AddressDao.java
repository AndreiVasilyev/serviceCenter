package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.exception.DaoException;

import java.util.List;

/**
 * The interface Address dao.
 */
public interface AddressDao extends BaseDao<Address> {

    /**
     * Find by all params list.
     *
     * @param address the address
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Address> findByAllParams(Address address) throws DaoException;

    /**
     * Create address long.
     *
     * @param address the address
     * @return the long
     * @throws DaoException the dao exception
     */
    long createAddress(Address address) throws DaoException;
}
