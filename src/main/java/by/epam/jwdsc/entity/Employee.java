package by.epam.jwdsc.entity;

import java.util.List;

/**
 * The type Employee.
 */
public class Employee extends AbstractUser {
    private String login;
    private String password;

    /**
     * Instantiates a new Employee.
     *
     * @param id         the id
     * @param firstName  the first name
     * @param secondName the second name
     * @param patronymic the patronymic
     * @param address    the address
     * @param phones     the phones
     * @param email      the email
     * @param userRole   the user role
     * @param login      the login
     * @param password   the password
     */
    public Employee(long id, String firstName, String secondName, String patronymic, Address address,
                    List<String> phones, String email, UserRole userRole, String login, String password) {
        super(id, firstName, secondName, patronymic, address, phones, email, userRole);
        this.login = login;
        this.password = password;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
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
