package by.epam.jwdsc.entity;

import java.math.BigDecimal;

public class SparePart extends CommonEntity {
    private long id;
    private String partNumber;
    private String name;
    private String description;
    private BigDecimal cost;

    private SparePart(long id, String partNumber, String name, String description, BigDecimal cost) {
        this.id = id;
        this.partNumber = partNumber;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SparePart sparePart = (SparePart) o;

        if (id != sparePart.id) return false;
        if (partNumber != null ? !partNumber.equals(sparePart.partNumber) : sparePart.partNumber != null) return false;
        if (!name.equals(sparePart.name)) return false;
        if (description != null ? !description.equals(sparePart.description) : sparePart.description != null)
            return false;
        return cost.equals(sparePart.cost);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (partNumber != null ? partNumber.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + cost.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SparePart{");
        sb.append("id=").append(id);
        sb.append(", partNumber='").append(partNumber).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private long id;
        private String partNumber;
        private final String name;
        private String description;
        private final BigDecimal cost;


        public Builder(String name, BigDecimal cost) {
            this.name = name;
            this.cost = cost;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder partNumber(String partNumber) {
            this.partNumber = partNumber;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public SparePart build() {
            return new SparePart(this.id, this.partNumber, this.name, this.description, this.cost);
        }
    }
}
