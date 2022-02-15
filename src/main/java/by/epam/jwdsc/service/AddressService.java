package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> findAddressesByParams(NewOrderData newOrderData) throws ServiceException;

    long createAddress(NewOrderData newOrderData) throws ServiceException;

    Optional<Address> findById(long id) throws ServiceException;

    Optional<Address> updateAddress(OrderData orderData) throws ServiceException;
}
