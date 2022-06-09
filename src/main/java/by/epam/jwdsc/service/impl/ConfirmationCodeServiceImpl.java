package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.CodeDao;
import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.PriceInfoDao;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.ConfirmationCodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Optional;

public class ConfirmationCodeServiceImpl implements ConfirmationCodeService {

    private static final Logger log = LogManager.getLogger();
    private CodeDao codeDao;

    public ConfirmationCodeServiceImpl() {
        this.codeDao = DaoProvider.getInstance().getCodeDao();
    }

    public ConfirmationCodeServiceImpl(CodeDao codeDao) {
        this.codeDao = codeDao;
    }

    @Override
    public boolean saveCode(String code, String email) throws ServiceException {
        try {
            Optional<String> existingCode = codeDao.findByEmail(email);
            if (existingCode.isPresent()) {
                codeDao.update(email, code);
            } else {
                codeDao.create(email, code);
            }
        } catch (DaoException e) {
            log.error("Error when saving confirmation code to DB", e);
            throw new ServiceException("Error when saving confirmation code to DB", e);
        }
        return true;
    }

    @Override
    public boolean verifyCode(String code, String email) throws ServiceException {
        boolean result = false;
        try {
            Optional<String> existingCode = codeDao.findByEmail(email);
            if (existingCode.isPresent() && code.equalsIgnoreCase(existingCode.get())) {
                result = true;
            }
        } catch (DaoException e) {
            log.error("Error when find confirmation code in DB", e);
            throw new ServiceException("Error when find confirmation code in DB", e);
        }
        return result;
    }
}
