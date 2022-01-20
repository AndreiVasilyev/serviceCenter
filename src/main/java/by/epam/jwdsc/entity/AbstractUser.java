package by.epam.jwdsc.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUser extends CommonEntity {
    private long id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private Address address;
    private List<String> phones;
    private String email;
    private UserRole userRole;

    public AbstractUser(long id, String firstName, String secondName, String patronymic,
                        Address address, List<String> phones, String email, UserRole userRole) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.address = address;
        this.phones = phones == null ? new ArrayList<>() : phones;
        this.email = email;
        this.userRole = userRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Address getAddress() {
        return address;
    }

    public List<String> getPhones() {
        return phones;
    }

    public boolean addPhone(String phone) {
        return phones.add(phone);
    }

    public String getEmail() {
        return email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractUser that = (AbstractUser) o;

        if (id != that.id) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!secondName.equals(that.secondName)) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phones != null ? !phones.equals(that.phones) : that.phones != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return userRole == that.userRole;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + userRole.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractUser{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", address=").append(address);
        sb.append(", phones=").append(phones);
        sb.append(", email='").append(email).append('\'');
        sb.append(", userRole=").append(userRole);
        sb.append('}');
        return sb.toString();
    }
}
