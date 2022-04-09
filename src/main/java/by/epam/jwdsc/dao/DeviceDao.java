package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.exception.DaoException;

import java.util.LinkedHashMap;
import java.util.List;

public interface DeviceDao extends BaseDao<Device> {
    List<Device> findByParametersWithSort(LinkedHashMap<String, Object> parameters, String sort) throws DaoException;

    long createDevice(Device device) throws DaoException;
}
