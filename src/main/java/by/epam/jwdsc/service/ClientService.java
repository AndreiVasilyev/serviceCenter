package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> findClientsByPhone(String phoneNumber) throws ServiceException;

    Optional<Client> findClientById(long userId) throws ServiceException;

    long createClient(NewOrderData newOrderData, Address addressId, List<String> phones) throws ServiceException;

    Optional<Client> updateClient(NewOrderData newOrderData, List<String> phones) throws ServiceException;
}
