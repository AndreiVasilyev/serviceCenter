package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;

public interface ClientService {

    List<Client> findClientsByPhone(String phoneNumber) throws ServiceException;
}
