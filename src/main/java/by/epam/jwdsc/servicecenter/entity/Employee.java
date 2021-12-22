package by.epam.jwdsc.servicecenter.entity;

import java.util.List;

public class Employee extends AbstractUser {
    private final String login;
    private final String password;
    private final EmployeeRole role;

    public Employee(long id, String firstName, String secondName, String patronymic, Address address,
                    List<String> phones, String email, String login, String password, EmployeeRole role) {
        super(id, firstName, secondName, patronymic, address, phones, email);
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public EmployeeRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        if (!login.equals(employee.login)) return false;
        if (!password.equals(employee.password)) return false;
        return role == employee.role;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append(super.toString());
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
