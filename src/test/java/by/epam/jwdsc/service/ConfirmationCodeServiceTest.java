package by.epam.jwdsc.service;

import by.epam.jwdsc.dao.impl.CodeDaoImpl;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.impl.ConfirmationCodeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


/**
 * The type Confirmation code service test.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfirmationCodeServiceTest {

    @Mock
    private CodeDaoImpl codeDao;
    @InjectMocks
    private ConfirmationCodeServiceImpl confirmationCodeService = new ConfirmationCodeServiceImpl();

    /**
     * Save code for existing with positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void saveCodeForExistingWithPositiveResult() throws ServiceException, DaoException {
        when(codeDao.findByEmail("testEmail@mail.com")).thenReturn(Optional.of("55555"));
        when(codeDao.update("testEmail@mail.com", "55555")).thenReturn("44444");
        boolean isSavedCode = confirmationCodeService.saveCode("55555", "testEmail@mail.com");
        assertThat(isSavedCode)
                .isTrue();
    }

    /**
     * Save code for new with positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void saveCodeForNewWithPositiveResult() throws ServiceException, DaoException {
        when(codeDao.findByEmail("testEmail@mail.com")).thenReturn(Optional.empty());
        when(codeDao.create("testEmail@mail.com", "55555")).thenReturn(true);
        boolean isSavedCode = confirmationCodeService.saveCode("55555", "testEmail@mail.com");
        assertThat(isSavedCode)
                .isTrue();
    }

    /**
     * Save code negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void saveCodeNegativeResult() throws ServiceException, DaoException {
        when(codeDao.findByEmail("testEmail@mail.com")).thenThrow(DaoException.class);
        confirmationCodeService.saveCode("55555", "testEmail@mail.com");
    }

    /**
     * Verify code positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void verifyCodePositiveResult() throws ServiceException, DaoException {
        when(codeDao.findByEmail("testEmail@mail.com")).thenReturn(Optional.of("55555"));
        boolean isSavedCode = confirmationCodeService.verifyCode("55555", "testEmail@mail.com");
        assertThat(isSavedCode)
                .isTrue();
    }

    /**
     * Verify code negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void verifyCodeNegativeResult() throws ServiceException, DaoException {
        when(codeDao.findByEmail("testEmail@mail.com")).thenReturn(Optional.of("55555"));
        boolean isSavedCode = confirmationCodeService.verifyCode("44444", "testEmail@mail.com");
        assertThat(isSavedCode)
                .isFalse();
    }

}
