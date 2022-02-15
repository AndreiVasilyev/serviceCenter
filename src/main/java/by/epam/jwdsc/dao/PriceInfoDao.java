package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.PriceInfo;
import by.epam.jwdsc.exception.DaoException;

import java.math.BigDecimal;
import java.util.Optional;

public interface PriceInfoDao extends BaseDao<PriceInfo> {
    Optional<BigDecimal> findCostByDeviceAndLevel(long deviceId, String repairLevel) throws DaoException;

    Optional<PriceInfo> findByDeviceAndLevel(long deviceId, String repairLevel) throws DaoException;
}
