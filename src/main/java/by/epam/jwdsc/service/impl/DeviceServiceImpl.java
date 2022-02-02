package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.DeviceDao;
import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.DeviceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DeviceServiceImpl implements DeviceService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public List<Device> findAll() throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        DeviceDao deviceDao = daoProvider.getDeviceDao();
        try {
            return deviceDao.findAll();
        } catch (DaoException e) {
            log.error("Error executing service find all devices", e);
            throw new ServiceException("Error executing service find all devices", e);
        }
    }
}
