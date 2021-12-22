package by.epam.jwdsc.servicecenter.entity;

import java.math.BigDecimal;

public class Device {

    private final long id;
    private final String name;
    private final RepairLevel repairLevel;
    private final BigDecimal repairCost;

    public Device(long id, String name, RepairLevel repairLevel, BigDecimal repairCost) {
        this.id = id;
        this.name = name;
        this.repairCost = repairCost;
        this.repairLevel = repairLevel;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RepairLevel getRepairLevel() {
        return repairLevel;
    }

    public BigDecimal getRepairCost() {
        return repairCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (id != device.id) return false;
        if (!name.equals(device.name)) return false;
        if (repairLevel != device.repairLevel) return false;
        return repairCost.equals(device.repairCost);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + repairLevel.hashCode();
        result = 31 * result + repairCost.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Device{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", repairLevel=").append(repairLevel);
        sb.append(", repairCost=").append(repairCost);
        sb.append('}');
        return sb.toString();
    }

}
