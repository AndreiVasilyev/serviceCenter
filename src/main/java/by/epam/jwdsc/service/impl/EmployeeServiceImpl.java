package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.entity.UserRole;
import by.epam.jwdsc.service.EmployeeService;

import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Optional<UserRole> authorize(String login, String password) {
        return Optional.empty();
    }
}
