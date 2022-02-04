package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.AddressDao;
import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.AddressService;
import by.epam.jwdsc.service.EntityMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public List<Address> findAddressesByParams(NewOrderData newOrderData) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        AddressDao addressDao = daoProvider.getAddressDao();
        EntityMapper entityMapper = EntityMapper.getInstance();
        Address addressTemplate = entityMapper.mapAddress(newOrderData);
        try {
            return addressDao.findByParams(addressTemplate);
        } catch (DaoException e) {
            log.error("Error executing service find addresses by params", e);
            throw new ServiceException("Error executing service find addresses by params", e);
        }
    }

    @Override
    public long createAddress(NewOrderData newOrderData) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        AddressDao addressDao = daoProvider.getAddressDao();
        EntityMapper entityMapper = EntityMapper.getInstance();
        Address addressTemplate = entityMapper.mapAddress(newOrderData);
        try {
            return addressDao.createAddress(addressTemplate);
        } catch (DaoException e) {
            log.error("Error executing service create new address", e);
            throw new ServiceException("Error executing service create new address", e);
        }
    }

    @Override
    public Optional<Address> findById(long id) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        AddressDao addressDao = daoProvider.getAddressDao();
        try {
            return addressDao.findById(id);
        } catch (DaoException e) {
            log.error("Error executing service find address by id", e);
            throw new ServiceException("Error executing service find address by id", e);
        }
    }

}
