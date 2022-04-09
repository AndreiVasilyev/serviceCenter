package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.entity.dto.DeviceData;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;

public interface DeviceService {

    List<Device> findAll() throws ServiceException;

    List<Device> findDevicesByParameters(DeviceData deviceData) throws ServiceException;

    long createDevice(String name) throws ServiceException;
}
