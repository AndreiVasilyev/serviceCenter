package by.epam.jwdsc.entity;

import java.util.List;

public class Employee extends AbstractUser {
    private String login;
    private String password;

    public Employee(long id, String firstName, String secondName, String patronymic, Address address,
                    List<String> phones, String email, UserRole userRole, String login, String password) {
        super(id, firstName, secondName, patronymic, address, phones, email, userRole);
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        if (!login.equals(employee.login)) return false;
        return password.equals(employee.password);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append(super.toString());
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
