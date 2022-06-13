package by.epam.jwdsc.service;

import by.epam.jwdsc.dao.impl.SparePartDaoImpl;
import by.epam.jwdsc.entity.SparePart;
import by.epam.jwdsc.entity.dto.SparePartData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.impl.SparePartServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * The type Spare part service test.
 */
@RunWith(MockitoJUnitRunner.class)
public class SparePartServiceTest {

    @Mock
    private SparePartDaoImpl sparePartDao;
    @InjectMocks
    private SparePartServiceImpl sparePartService = new SparePartServiceImpl();
    private SparePart testSparePart;

    /**
     * Sets up.
     *
     * @throws DaoException the dao exception
     */
    @Before
    public void setUp() throws DaoException {
        testSparePart = new SparePart.Builder("testName", new BigDecimal("50.50"))
                .partNumber("testNumber")
                .description("testDesc")
                .build();
    }

    /**
     * Find parts by param positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findPartsByParamPositiveResult() throws ServiceException, DaoException {
        when(sparePartDao.findByParam("test")).thenReturn(new ArrayList<>(Collections.nCopies(5, testSparePart)));
        List<SparePart> foundSpareParts = sparePartService.findPartsByParam("test");
        assertThat(foundSpareParts)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .element(0)
                .hasFieldOrPropertyWithValue("partNumber", "testNumber")
                .hasFieldOrPropertyWithValue("name", "testName");
    }

    /**
     * Find parts by param negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findPartsByParamNegativeResult() throws ServiceException, DaoException {
        when(sparePartDao.findByParam("test")).thenReturn(new ArrayList<>());
        List<SparePart> foundSpareParts = sparePartService.findPartsByParam("test");
        assertThat(foundSpareParts)
                .isNotNull()
                .isEmpty();
    }

    /**
     * Find parts by parameters positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findPartsByParametersPositiveResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("s.name LIKE ? ", "%testName%");
        SparePartData sparePartData = new SparePartData();
        sparePartData.setName("testName");
        when(sparePartDao.findByParametersWithSort(params, "")).thenReturn(new ArrayList<>(Collections.nCopies(3, testSparePart)));
        List<SparePart> foundSpareParts = sparePartService.findPartsByParameters(sparePartData);
        assertThat(foundSpareParts)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .element(0)
                .hasFieldOrPropertyWithValue("partNumber", "testNumber")
                .hasFieldOrPropertyWithValue("name", "testName");
    }

    /**
     * Find parts by parameters negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findPartsByParametersNegativeResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("s.name LIKE ? ", "%testName%");
        SparePartData sparePartData = new SparePartData();
        sparePartData.setName("test");
        when(sparePartDao.findByParametersWithSort(params, "")).thenReturn(new ArrayList<>());
        List<SparePart> foundSpareParts = sparePartService.findPartsByParameters(sparePartData);
        assertThat(foundSpareParts)
                .isNotNull()
                .isEmpty();
    }

    /**
     * Add new part positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void addNewPartPositiveResult() throws ServiceException, DaoException {
        SparePartData sparePartData = new SparePartData();
        sparePartData.setName("testName");
        sparePartData.setPartNumber("testNumber");
        sparePartData.setDescription("testDesc");
        sparePartData.setCost("50.50");
        when(sparePartDao.create(testSparePart)).thenReturn(true);
        boolean isCreatedPart = sparePartService.addNewPart(sparePartData);
        assertThat(isCreatedPart)
                .isTrue();
    }

    /**
     * Add new part negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void addNewPartNegativeResult() throws ServiceException, DaoException {
        SparePartData sparePartData = new SparePartData();
        sparePartData.setName("testName");
        sparePartData.setPartNumber("testNumber");
        sparePartData.setDescription("testDesc");
        sparePartData.setCost("50.50");
        when(sparePartDao.create(testSparePart)).thenThrow(DaoException.class);
        sparePartService.addNewPart(sparePartData);
    }
}
