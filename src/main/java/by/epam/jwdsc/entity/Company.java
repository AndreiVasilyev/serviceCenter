package by.epam.jwdsc.entity;

public class Company {
    private final long id;
    private final String name;
    private final boolean isContract;

    public Company(long id, String name, boolean isContract) {
        this.id = id;
        this.name = name;
        this.isContract = isContract;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isContract() {
        return isContract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != company.id) return false;
        if (isContract != company.isContract) return false;
        return name.equals(company.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (isContract ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", isContract=").append(isContract);
        sb.append('}');
        return sb.toString();
    }
}
