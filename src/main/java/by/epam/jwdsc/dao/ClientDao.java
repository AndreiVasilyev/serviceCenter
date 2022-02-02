package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.exception.DaoException;

import java.util.List;

public interface ClientDao extends BaseDao<Client> {

    List<Client> findByPhone(String phoneNumber) throws DaoException;
}
