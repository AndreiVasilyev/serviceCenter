package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.DeviceDao;
import by.epam.jwdsc.dao.QueryParametersMapper;
import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.entity.dto.DeviceData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.DeviceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;

public class DeviceServiceImpl implements DeviceService {

    private static final Logger log = LogManager.getLogger();
    private DeviceDao deviceDao;

    public DeviceServiceImpl() {
        this.deviceDao = DaoProvider.getInstance().getDeviceDao();
    }

    public DeviceServiceImpl(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Override
    public List<Device> findAll() throws ServiceException {
        try {
            return deviceDao.findAll();
        } catch (DaoException e) {
            log.error("Error executing service find all devices", e);
            throw new ServiceException("Error executing service find all devices", e);
        }
    }

    @Override
    public List<Device> findDevicesByParameters(DeviceData deviceData) throws ServiceException {
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> parameters = queryParametersMapper.mapDeviceParameters(deviceData);
        String sort = queryParametersMapper.mapDeviceSort(deviceData);
        try {
            return deviceDao.findByParametersWithSort(parameters, sort);
        } catch (DaoException e) {
            log.error("Error executing service find devices by parameters with sort");
            throw new ServiceException("Error executing service find devices by parameters with sort", e);
        }
    }

    @Override
    public long createDevice(String name) throws ServiceException {
        try {
            return deviceDao.createDevice(new Device(name));
        } catch (DaoException e) {
            log.error("Error executing service create devices", e);
            throw new ServiceException("Error executing service create devices", e);
        }
    }


}
