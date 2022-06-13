package by.epam.jwdsc.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Abstract user.
 */
public abstract class AbstractUser extends CommonEntity {
    private long id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private Address address;
    private List<String> phones;
    private String email;
    private UserRole userRole;

    /**
     * Instantiates a new Abstract user.
     *
     * @param id         the id
     * @param firstName  the first name
     * @param secondName the second name
     * @param patronymic the patronymic
     * @param address    the address
     * @param phones     the phones
     * @param email      the email
     * @param userRole   the user role
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets second name.
     *
     * @return the second name
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Gets patronymic.
     *
     * @return the patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets phones.
     *
     * @return the phones
     */
    public List<String> getPhones() {
        return phones;
    }

    /**
     * Add phone boolean.
     *
     * @param phone the phone
     * @return the boolean
     */
    public boolean addPhone(String phone) {
        return phones.add(phone);
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets second name.
     *
     * @param secondName the second name
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Sets patronymic.
     *
     * @param patronymic the patronymic
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Sets phones.
     *
     * @param phones the phones
     */
    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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
