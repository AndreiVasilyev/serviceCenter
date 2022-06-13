package by.epam.jwdsc.entity;

import java.util.List;

/**
 * The type Client.
 */
public class Client extends AbstractUser {
    private int discount;

    /**
     * Instantiates a new Client.
     *
     * @param id         the id
     * @param firstName  the first name
     * @param secondName the second name
     * @param patronymic the patronymic
     * @param address    the address
     * @param phones     the phones
     * @param email      the email
     * @param userRole   the user role
     * @param discount   the discount
     */
    public Client(long id, String firstName, String secondName, String patronymic, Address address,
                  List<String> phones, String email, UserRole userRole, int discount) {
        super(id, firstName, secondName, patronymic, address, phones, email, userRole);
        this.discount = discount;
    }

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        return discount == client.discount;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + discount;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append(super.toString());
        sb.append("discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }
}
