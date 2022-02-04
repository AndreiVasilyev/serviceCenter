package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.exception.DaoException;

public interface DeviceDao extends BaseDao<Device> {
    long createDevice(Device device) throws DaoException;
}
