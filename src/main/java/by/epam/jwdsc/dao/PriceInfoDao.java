package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.PriceInfo;
import by.epam.jwdsc.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface Price info dao.
 */
public interface PriceInfoDao extends BaseDao<PriceInfo> {
    /**
     * Find cost by device and level optional.
     *
     * @param deviceId    the device id
     * @param repairLevel the repair level
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<BigDecimal> findCostByDeviceAndLevel(long deviceId, String repairLevel) throws DaoException;

    /**
     * Find by device and level optional.
     *
     * @param deviceId    the device id
     * @param repairLevel the repair level
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<PriceInfo> findByDeviceAndLevel(long deviceId, String repairLevel) throws DaoException;

    /**
     * Find costs by device list.
     *
     * @param deviceId the device id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<PriceInfo> findCostsByDevice(long deviceId) throws DaoException;
}
