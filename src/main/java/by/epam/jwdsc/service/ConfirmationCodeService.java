package by.epam.jwdsc.service;

import by.epam.jwdsc.exception.ServiceException;

/**
 * The interface Confirmation code service.
 */
public interface ConfirmationCodeService {

    /**
     * Save code boolean.
     *
     * @param code  the code
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean saveCode(String code, String email) throws ServiceException;

    /**
     * Verify code boolean.
     *
     * @param code  the code
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean verifyCode(String code, String email) throws ServiceException;
}
