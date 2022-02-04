package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.exception.DaoException;

import java.util.List;

public interface AddressDao extends BaseDao<Address> {

    List<Address> findByParams(Address address) throws DaoException;

    long createAddress(Address address) throws DaoException;
}
