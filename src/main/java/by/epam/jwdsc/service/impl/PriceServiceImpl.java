package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.PriceInfoDao;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.PriceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

public class PriceServiceImpl implements PriceService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Optional<BigDecimal> findCostByDeviceAndLevel(long deviceId, String repairLevel) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        PriceInfoDao priceInfoDao = daoProvider.getPriceInfoDao();
        try {
            return priceInfoDao.findCostByDeviceAndLevel(deviceId, repairLevel);
        } catch (DaoException e) {
            log.error("Error executing service find work cost by device id and repair level");
            throw new ServiceException("Error executing service find work cost by device id and repair level", e);
        }
    }
}
