package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.entity.dto.DeviceData;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;

/**
 * The interface Device service.
 */
public interface DeviceService {

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Device> findAll() throws ServiceException;

    /**
     * Find devices by parameters list.
     *
     * @param deviceData the device data
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Device> findDevicesByParameters(DeviceData deviceData) throws ServiceException;

    /**
     * Create device long.
     *
     * @param name the name
     * @return the long
     * @throws ServiceException the service exception
     */
    long createDevice(String name) throws ServiceException;
}
