package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.UserRole;

import java.util.Optional;

public interface EmployeeService {

    Optional<UserRole> authorize(String login, String password);
}
