package by.epam.jwdsc.servicecenter.entity;

import java.util.List;

public class Client extends AbstractUser {
    private final int discount;

    public Client(long id, String firstName, String secondName, String patronymic, Address address,
                  List<String> phones, String email, int discount) {
        super(id, firstName, secondName, patronymic, address, phones, email);
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
