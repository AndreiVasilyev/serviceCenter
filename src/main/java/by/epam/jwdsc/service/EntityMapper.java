package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.entity.dto.EmployeeParameters;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityMapper {

    private static EntityMapper instance;
    public static final String PHONES_DELIMITER = ",";

    private EntityMapper() {
    }

    public static EntityMapper getInstance() {
        if (instance == null) {
            instance = new EntityMapper();
        }
        return instance;
    }

    public Client mapClient(NewOrderData newOrderData, Address address, List<String> phones) {
        return UserBuilders.newClient()
                .firstName(newOrderData.getFirstName())
                .secondName(newOrderData.getSecondName())
                .patronymic(newOrderData.getPatronymic())
                .email(newOrderData.getEmail())
                .address(address)
                .phones(phones)
                .build();
    }

    public Client mapClient(OrderData orderData, Address address, List<String> phones) {
        long id = Long.parseLong(orderData.getClientId());
        return UserBuilders.newClient()
                .id(id)
                .firstName(orderData.getFirstName())
                .secondName(orderData.getSecondName())
                .patronymic(orderData.getPatronymic())
                .email(orderData.getEmail())
                .address(address)
                .phones(phones)
                .build();
    }

    public Address mapAddress(NewOrderData newOrderData) {
        int houseNumber = Integer.parseInt(newOrderData.getHouseNumber());
        Address address = new Address.Builder(newOrderData.getCity(), newOrderData.getStreet(), houseNumber)
                .country(newOrderData.getCountry())
                .state(newOrderData.getState())
                .region(newOrderData.getRegion())
                .build();
        if (!newOrderData.getPostcode().isBlank()) {
            int postcode = Integer.parseInt(newOrderData.getPostcode());
            address.setPostcode(postcode);
        }
        if (!newOrderData.getApartmentNumber().isBlank()) {
            int apartmentNumber = Integer.parseInt(newOrderData.getApartmentNumber());
            address.setApartmentNumber(apartmentNumber);
        }
        return address;
    }

    public Address mapAddress(OrderData orderData) {
        long id = Long.parseLong(orderData.getAddressId());
        int houseNumber = Integer.parseInt(orderData.getHouseNumber());
        Address address = new Address.Builder(orderData.getCity(), orderData.getStreet(), houseNumber)
                .country(orderData.getCountry())
                .state(orderData.getState())
                .region(orderData.getRegion())
                .id(id)
                .build();
        if (!orderData.getPostcode().isBlank()) {
            int postcode = Integer.parseInt(orderData.getPostcode());
            address.setPostcode(postcode);
        }
        if (!orderData.getApartmentNumber().isBlank()) {
            int apartmentNumber = Integer.parseInt(orderData.getApartmentNumber());
            address.setApartmentNumber(apartmentNumber);
        }
        return address;
    }

    public Employee mapEmployee(EmployeeParameters employeeParameters, String hashedPassword) {
        long userId = Long.parseLong(employeeParameters.getId());
        int houseNumber = Integer.parseInt(employeeParameters.getHouseNumber());
        UserRole userRole = UserRole.valueOf(employeeParameters.getUserRole());
        String[] splitPhones = employeeParameters.getPhones().split(PHONES_DELIMITER);
        List<String> phones = List.of(splitPhones);
        Address address = new Address.Builder(employeeParameters.getCity(), employeeParameters.getStreet(), houseNumber)
                .country(employeeParameters.getCountry())
                .state(employeeParameters.getState())
                .region(employeeParameters.getRegion())
                .build();
        if (!employeeParameters.getPostcode().isBlank()) {
            int postcode = Integer.parseInt(employeeParameters.getPostcode());
            address.setPostcode(postcode);
        }
        if (!employeeParameters.getApartmentNumber().isBlank()) {
            int apartmentNumber = Integer.parseInt(employeeParameters.getApartmentNumber());
            address.setApartmentNumber(apartmentNumber);
        }
        return UserBuilders.newEmployee()
                .id(userId)
                .login(employeeParameters.getLogin())
                .password(hashedPassword)
                .email(employeeParameters.getEmail())
                .firstName(employeeParameters.getFirstName())
                .secondName(employeeParameters.getSecondName())
                .patronymic(employeeParameters.getPatronymic())
                .userRole(userRole)
                .address(address)
                .phones(phones)
                .build();
    }
}
