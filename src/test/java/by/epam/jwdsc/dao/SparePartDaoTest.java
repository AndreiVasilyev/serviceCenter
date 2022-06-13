package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.SparePart;
import by.epam.jwdsc.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Spare part dao test.
 */
public class SparePartDaoTest {

    private SparePartDao sparePartDao;
    private SparePart testSparePart;


    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        sparePartDao = DaoProvider.getInstance().getSparePartDao();
        testSparePart = new SparePart.Builder("testName", new BigDecimal("50.50"))
                .partNumber("testNumber")
                .description("testDesc")
                .build();
    }

    /**
     * Find by id positive result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findById_positiveResult() throws DaoException {
        Optional<SparePart> foundParts = sparePartDao.findById(2);
        assertThat(foundParts).isPresent()
                .get()
                .hasFieldOrProperty("name")
                .hasFieldOrPropertyWithValue("name", "Дисплей");
    }

    /**
     * Find by id negative result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findById_negativeResult() throws DaoException {
        Optional<SparePart> foundParts = sparePartDao.findById(0);
        assertThat(foundParts).isEmpty();
    }

    /**
     * Find all.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findAll() throws DaoException {
        List<SparePart> foundParts = sparePartDao.findAll();
        assertThat(foundParts)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(10);
    }

    /**
     * Find by param positive result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findByParamPositiveResult() throws DaoException {
        String searchParameter = "te";
        List<SparePart> foundParts = sparePartDao.findByParam(searchParameter);
        assertThat(foundParts)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .element(0)
                .hasFieldOrPropertyWithValue("name", "Микрофон");
    }

    /**
     * Find by param negative result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findByParamNegativeResult() throws DaoException {
        String searchParameter = "333";
        List<SparePart> foundParts = sparePartDao.findByParam(searchParameter);
        assertThat(foundParts)
                .isNotNull()
                .isEmpty();
    }

    /**
     * Find by parameters with sort positive result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findByParametersWithSortPositiveResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("s.part_number LIKE ? ", "%1%");
        String sortParam = "s.name";
        List<SparePart> foundParts = sparePartDao.findByParametersWithSort(parameters, "");
        List<SparePart> foundPartsWithSort = sparePartDao.findByParametersWithSort(parameters, sortParam);
        assertThat(foundParts)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1);
        assertThat(foundPartsWithSort)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1)
                .hasSize(foundParts.size())
                .containsAll(foundParts)
                .isSortedAccordingTo(Comparator.comparing(SparePart::getName));
        sortParam = "s.name DESC";
        foundPartsWithSort = sparePartDao.findByParametersWithSort(parameters, sortParam);
        assertThat(foundPartsWithSort)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1)
                .hasSize(foundParts.size())
                .containsAll(foundParts)
                .isSortedAccordingTo((o1, o2) -> o2.getName().compareTo(o1.getName()));
    }

    /**
     * Find by parameters with sort with exception.
     *
     * @throws DaoException the dao exception
     */
    @Test(expected = DaoException.class)
    public void findByParametersWithSortWithException() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("s.part_number", "%1%");
        String sortParam = "s.name";
        sparePartDao.findByParametersWithSort(parameters, sortParam);
    }

    /**
     * Create.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void create() throws DaoException {
        boolean isCreatedPart = sparePartDao.create(testSparePart);
        assertThat(isCreatedPart)
                .isTrue();
        SparePart createdPart = sparePartDao.findByParam("testDesc").get(0);
        sparePartDao.delete(createdPart);
    }

    /**
     * Update.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void update() throws DaoException {
        sparePartDao.create(testSparePart);
        SparePart createdPart = sparePartDao.findByParam("testDesc").get(0);
        createdPart.setDescription("testDescUpdated");
        Optional<SparePart> oldPart = sparePartDao.update(createdPart);
        List<SparePart> newParts = sparePartDao.findByParam("testDescUpdated");
        assertThat(oldPart)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("description", "testDesc");
        assertThat(newParts)
                .isNotNull()
                .isNotEmpty()
                .element(0)
                .hasFieldOrPropertyWithValue("description", "testDescUpdated");
        sparePartDao.delete(newParts.get(0));
    }

    /**
     * Delete.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void delete() throws DaoException {
        sparePartDao.create(testSparePart);
        SparePart createdPart = sparePartDao.findByParam("testDesc").get(0);
        boolean isDeleted = sparePartDao.delete(createdPart);
        assertThat(isDeleted)
                .isTrue();
    }

    /**
     * Delete by id.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void deleteById() throws DaoException {
        sparePartDao.create(testSparePart);
        SparePart createdPart = sparePartDao.findByParam("testDesc").get(0);
        boolean isDeleted = sparePartDao.deleteById(createdPart.getId());
        assertThat(isDeleted)
                .isTrue();
    }
}
