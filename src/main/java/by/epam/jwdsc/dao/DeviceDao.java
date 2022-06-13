package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.exception.DaoException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The interface Device dao.
 */
public interface DeviceDao extends BaseDao<Device> {
    /**
     * Find by parameters with sort list.
     *
     * @param parameters the parameters
     * @param sort       the sort
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Device> findByParametersWithSort(LinkedHashMap<String, Object> parameters, String sort) throws DaoException;

    /**
     * Create device long.
     *
     * @param device the device
     * @return the long
     * @throws DaoException the dao exception
     */
    long createDevice(Device device) throws DaoException;
}
