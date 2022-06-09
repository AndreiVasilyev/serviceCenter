package by.epam.jwdsc.service;

import by.epam.jwdsc.dao.impl.DeviceDaoImpl;
import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.entity.SparePart;
import by.epam.jwdsc.entity.dto.DeviceData;
import by.epam.jwdsc.entity.dto.SparePartData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.impl.DeviceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {

    @Mock
    private DeviceDaoImpl deviceDao;
    @InjectMocks
    private DeviceServiceImpl deviceService = new DeviceServiceImpl();
    private Device testDevice;

    @Before
    public void setUp() throws DaoException {
        testDevice = new Device("testDevice");
    }

    @Test
    public void findAllPositiveResult() throws ServiceException, DaoException {
        when(deviceDao.findAll()).thenReturn(new ArrayList<>(Collections.nCopies(5, testDevice)));
        List<Device> foundDevices = deviceService.findAll();
        assertThat(foundDevices)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .element(0)
                .hasFieldOrPropertyWithValue("name", "testDevice");
    }

    @Test(expected = ServiceException.class)
    public void findAllNegativeResult() throws ServiceException, DaoException {
        when(deviceDao.findAll()).thenThrow(DaoException.class);
        deviceService.findAll();
    }

    @Test
    public void findDevicesByParametersPositiveResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("d.device_name LIKE ? ", "%testName%");
        DeviceData deviceData = new DeviceData();
        deviceData.setName("testName");
        when(deviceDao.findByParametersWithSort(params, "")).thenReturn(new ArrayList<>(Collections.nCopies(5, testDevice)));
        List<Device> foundDevices = deviceService.findDevicesByParameters(deviceData);
        assertThat(foundDevices)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .element(0)
                .hasFieldOrPropertyWithValue("name", "testDevice");
    }

    @Test
    public void findDevicesByParametersNegativeResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("d.device_name LIKE ? ", "%testName%");
        DeviceData deviceData = new DeviceData();
        deviceData.setName("test");
        when(deviceDao.findByParametersWithSort(params, "")).thenReturn(new ArrayList<>());
        List<Device> foundDevices = deviceService.findDevicesByParameters(deviceData);
        assertThat(foundDevices)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void createDevicePositiveResult() throws ServiceException, DaoException {
        when(deviceDao.createDevice(testDevice)).thenReturn(5L);
        long createdDeviceId = deviceService.createDevice("testDevice");
        assertThat(createdDeviceId)
                .isNotZero()
                .isPositive()
                .isEqualTo(5L);
    }

    @Test(expected = ServiceException.class)
    public void createDeviceNegativeResult() throws ServiceException, DaoException {
        when(deviceDao.createDevice(testDevice)).thenThrow(DaoException.class);
        deviceService.createDevice("testDevice");
    }
}
