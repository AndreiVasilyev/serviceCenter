package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.ClientDao;
import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public List<Client> findClientsByPhone(String phoneNumber) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        ClientDao clientDao = daoProvider.getClientDao();
        try {
            return clientDao.findByPhone(phoneNumber);
        } catch (DaoException e) {
            log.error("Error executing service find clients by phone number");
            throw new ServiceException("Error executing service find clients by phone number", e);
        }
    }
}
