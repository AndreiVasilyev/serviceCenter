package by.epam.jwdsc.entity;

/**
 * The type Company.
 */
public class Company extends CommonEntity {
    private long id;
    private String name;
    private boolean isContract;

    /**
     * Instantiates a new Company.
     *
     * @param name       the name
     * @param isContract the is contract
     */
    public Company(String name, boolean isContract) {
        this.name = name;
        this.isContract = isContract;
    }

    /**
     * Instantiates a new Company.
     *
     * @param id         the id
     * @param name       the name
     * @param isContract the is contract
     */
    public Company(long id, String name, boolean isContract) {
        this.id = id;
        this.name = name;
        this.isContract = isContract;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Is contract boolean.
     *
     * @return the boolean
     */
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
        return name != null ? name.equals(company.name) : company.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
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
