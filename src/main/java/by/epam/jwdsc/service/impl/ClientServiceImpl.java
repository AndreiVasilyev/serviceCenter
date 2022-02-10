package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.ClientDao;
import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.ClientService;
import by.epam.jwdsc.service.EntityMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Client> findClientById(long userId) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        ClientDao clientDao = daoProvider.getClientDao();
        try {
            return clientDao.findById(userId);
        } catch (DaoException e) {
            log.error("Error executing service find client by id");
            throw new ServiceException("Error executing service find client by id", e);
        }
    }

    @Override
    public long createClient(NewOrderData newOrderData, Address address, List<String> phones) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        ClientDao clientDao = daoProvider.getClientDao();
        EntityMapper entityMapper = EntityMapper.getInstance();
        Client client = entityMapper.mapClient(newOrderData, address, phones);
        try {
            return clientDao.createClient(client);
        } catch (DaoException e) {
            log.error("Error executing service create client");
            throw new ServiceException("Error executing service create client", e);
        }
    }

    @Override
    public Optional<Client> updateClient(NewOrderData newOrderData, List<String> phones) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        ClientDao clientDao = daoProvider.getClientDao();
        EntityMapper entityMapper = EntityMapper.getInstance();
        long clientId = Long.parseLong(newOrderData.getClientId());
        try {
            Client oldClient = findClientById(clientId).get();
            Address address = entityMapper.mapAddress(newOrderData);
            address.setId(oldClient.getAddress().getId());
            Client newClient = entityMapper.mapClient(newOrderData, address, phones);
            return clientDao.update(newClient);
        } catch (DaoException e) {
            log.error("Error executing service update client");
            throw new ServiceException("Error executing service update client", e);
        }
    }
}
