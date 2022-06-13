package by.epam.jwdsc.dao;

import by.epam.jwdsc.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Code dao test.
 */
public class CodeDaoTest {
    private CodeDao codeDao;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        codeDao = DaoProvider.getInstance().getCodeDao();
    }

    /**
     * Find all.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findAll() throws DaoException {
        Map<String, String> foundCodes = codeDao.findAll();
        assertThat(foundCodes)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(2);
    }

    /**
     * Find by email positive result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findByEmailPositiveResult() throws DaoException {
        Optional<String> foundCode = codeDao.findByEmail("petrov@mail.ru");
        assertThat(foundCode)
                .isPresent()
                .get()
                .isEqualTo("64898");
    }

    /**
     * Find by id negative result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findByIdNegativeResult() throws DaoException {
        Optional<String> foundCode = codeDao.findByEmail("petrov");
        assertThat(foundCode)
                .isEmpty();
    }

    /**
     * Create.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void create() throws DaoException {
        boolean isCreatedCode = codeDao.create("testEmail", "45612");
        assertThat(isCreatedCode)
                .isTrue();
        Optional<String> createdCode = codeDao.findByEmail("testEmail");
        assertThat(createdCode)
                .isPresent()
                .get()
                .isEqualTo("45612");
        codeDao.deleteByCode(createdCode.get());
    }

    /**
     * Update.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void update() throws DaoException {
        codeDao.create("testEmail", "45612");
        String oldCode = codeDao.update("testEmail", "44444");
        assertThat(oldCode)
                .isNotNull()
                .isEqualTo("45612");
        Optional<String> updatedCode = codeDao.findByEmail("testEmail");
        assertThat(updatedCode)
                .isPresent()
                .get()
                .isEqualTo("44444");
        codeDao.deleteByCode("44444");
    }

    /**
     * Delete by code.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void deleteByCode() throws DaoException {
        codeDao.create("testEmail", "45612");
        boolean isDeleted = codeDao.deleteByCode("45612");
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
        codeDao.create("testEmail", "45612");
        boolean isDeleted = codeDao.deleteByEmail("testEmail");
        assertThat(isDeleted)
                .isTrue();
    }
}
