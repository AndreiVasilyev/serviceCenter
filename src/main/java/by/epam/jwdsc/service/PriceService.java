package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.PriceInfo;
import by.epam.jwdsc.entity.dto.PricesData;
import by.epam.jwdsc.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface Price service.
 */
public interface PriceService {
    /**
     * Find cost by device and level optional.
     *
     * @param deviceId    the device id
     * @param repairLevel the repair level
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<BigDecimal> findCostByDeviceAndLevel(long deviceId, String repairLevel) throws ServiceException;

    /**
     * Find prices by device list.
     *
     * @param deviceId the device id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<PriceInfo> findPricesByDevice(long deviceId) throws ServiceException;

    /**
     * Save prices by device boolean.
     *
     * @param pricesData the prices data
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean savePricesByDevice(PricesData pricesData) throws ServiceException;
}
