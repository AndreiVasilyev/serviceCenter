package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.PriceInfo;
import by.epam.jwdsc.entity.dto.PricesData;
import by.epam.jwdsc.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PriceService {
    Optional<BigDecimal> findCostByDeviceAndLevel(long deviceId, String repairLevel) throws ServiceException;

    List<PriceInfo> findPricesByDevice(long deviceId) throws ServiceException;

    boolean savePricesByDevice(PricesData pricesData) throws ServiceException;
}
