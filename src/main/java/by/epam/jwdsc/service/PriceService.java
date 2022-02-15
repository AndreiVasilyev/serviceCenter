package by.epam.jwdsc.service;

import by.epam.jwdsc.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Optional;

public interface PriceService {
    Optional<BigDecimal> findCostByDeviceAndLevel(long deviceId, String repairLevel) throws ServiceException;
}
