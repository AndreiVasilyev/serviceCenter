package by.epam.jwdsc.entity;

import java.util.List;

public class Client extends AbstractUser {
    private int discount;

    public Client(long id, String firstName, String secondName, String patronymic, Address address,
                  List<String> phones, String email, UserRole userRole, int discount) {
        super(id, firstName, secondName, patronymic, address, phones, email, userRole);
        this.discount = discount;
    }

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
