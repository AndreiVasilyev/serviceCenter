package by.epam.jwdsc.service;

import by.epam.jwdsc.exception.ServiceException;

public interface ConfirmationCodeService {

    boolean saveCode(String code, String email) throws ServiceException;

    boolean verifyCode(String code, String email) throws ServiceException;
}
