package by.epam.jwdsc.service;

import by.epam.jwdsc.dao.impl.PriceInfoDaoImpl;
import by.epam.jwdsc.entity.PriceInfo;
import by.epam.jwdsc.entity.RepairLevel;
import by.epam.jwdsc.entity.dto.PricesData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.impl.PriceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * The type Price service test.
 */
@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {

    @Mock
    private PriceInfoDaoImpl priceInfoDao;
    @InjectMocks
    private PriceServiceImpl priceService = new PriceServiceImpl();
    private PriceInfo testPriceInfo;

    /**
     * Sets up.
     *
     * @throws DaoException the dao exception
     */
    @Before
    public void setUp() throws DaoException {
        testPriceInfo = new PriceInfo(5, RepairLevel.REPAIR_LEVEL_3, new BigDecimal("80"));
    }

    /**
     * Find cost by device and level positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findCostByDeviceAndLevelPositiveResult() throws ServiceException, DaoException {
        when(priceInfoDao.findCostByDeviceAndLevel(5L, "DIAGNOSTIC")).thenReturn(Optional.of(new BigDecimal("50.50")));
        Optional<BigDecimal> foundCost = priceService.findCostByDeviceAndLevel(5L, "DIAGNOSTIC");
        assertThat(foundCost)
                .isPresent()
                .get()
                .isEqualTo(new BigDecimal("50.50"));
    }

    /**
     * Find cost by device and level negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findCostByDeviceAndLevelNegativeResult() throws ServiceException, DaoException {
        when(priceInfoDao.findCostByDeviceAndLevel(5L, "DIAGNOSTIC")).thenReturn(Optional.empty());
        Optional<BigDecimal> foundCost = priceService.findCostByDeviceAndLevel(2L, "DIAGNOSTIC");
        assertThat(foundCost)
                .isEmpty();
    }

    /**
     * Find prices by device positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findPricesByDevicePositiveResult() throws ServiceException, DaoException {
        when(priceInfoDao.findCostsByDevice(5L)).thenReturn(new ArrayList<>(Collections.nCopies(5, testPriceInfo)));
        List<PriceInfo> foundPrices = priceService.findPricesByDevice(5L);
        assertThat(foundPrices)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .element(0)
                .hasFieldOrPropertyWithValue("repairLevel", RepairLevel.REPAIR_LEVEL_3)
                .hasFieldOrPropertyWithValue("repairCost", new BigDecimal("80"));
    }

    /**
     * Find prices by device negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findPricesByDeviceNegativeResult() throws ServiceException, DaoException {
        when(priceInfoDao.findCostsByDevice(5L)).thenReturn(new ArrayList<>());
        List<PriceInfo> foundPrices = priceService.findPricesByDevice(5L);
        assertThat(foundPrices)
                .isNotNull()
                .isEmpty();
    }

    /**
     * Save prices by device for existing with positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void savePricesByDeviceForExistingWithPositiveResult() throws ServiceException, DaoException {
        PricesData pricesData = new PricesData();
        pricesData.setDevice("1");
        pricesData.setDiagnostic("2");
        pricesData.setMaintenance("3");
        pricesData.setRepairLevel1("4");
        pricesData.setRepairLevel2("5");
        pricesData.setRepairLevel3("6");
        pricesData.setTechnicalConclusion("7");
        when(priceInfoDao.findByDeviceAndLevel(anyLong(), anyString())).thenReturn(Optional.of(testPriceInfo));
        when(priceInfoDao.update(testPriceInfo)).thenReturn(Optional.of(testPriceInfo));
        boolean isSavedPrice = priceService.savePricesByDevice(pricesData);
        assertThat(isSavedPrice)
                .isTrue();
    }

    /**
     * Save prices by device for new with positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void savePricesByDeviceForNewWithPositiveResult() throws ServiceException, DaoException {
        PricesData pricesData = new PricesData();
        pricesData.setDevice("1");
        pricesData.setDiagnostic("2");
        pricesData.setMaintenance("3");
        pricesData.setRepairLevel1("4");
        pricesData.setRepairLevel2("5");
        pricesData.setRepairLevel3("6");
        pricesData.setTechnicalConclusion("7");
        when(priceInfoDao.findByDeviceAndLevel(anyLong(), anyString())).thenReturn(Optional.empty());
        when(priceInfoDao.create(testPriceInfo)).thenReturn(true);
        boolean isSavedPrice = priceService.savePricesByDevice(pricesData);
        assertThat(isSavedPrice)
                .isTrue();
    }

    /**
     * Save prices by device negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void savePricesByDeviceNegativeResult() throws ServiceException, DaoException {
        PricesData pricesData = new PricesData();
        pricesData.setDevice("1");
        pricesData.setDiagnostic("2");
        pricesData.setMaintenance("3");
        pricesData.setRepairLevel1("4");
        pricesData.setRepairLevel2("5");
        pricesData.setRepairLevel3("6");
        pricesData.setTechnicalConclusion("7");
        when(priceInfoDao.findByDeviceAndLevel(anyLong(), anyString())).thenThrow(DaoException.class);
        when(priceInfoDao.update(testPriceInfo)).thenReturn(Optional.of(testPriceInfo));
        priceService.savePricesByDevice(pricesData);
    }
}
