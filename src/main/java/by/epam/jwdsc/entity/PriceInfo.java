package by.epam.jwdsc.entity;

import java.math.BigDecimal;

public class PriceInfo extends CommonEntity {

    private long id;
    private long device;
    private RepairLevel repairLevel;
    private BigDecimal repairCost;


    public PriceInfo(long id, long device, RepairLevel repairLevel, BigDecimal repairCost) {
        this.id = id;
        this.device = device;
        this.repairLevel = repairLevel;
        this.repairCost = repairCost;
    }

    public PriceInfo(long device, RepairLevel repairLevel, BigDecimal repairCost) {
        this.device = device;
        this.repairLevel = repairLevel;
        this.repairCost = repairCost;
    }

    public long getId() {
        return id;
    }

    public long getDevice() {
        return device;
    }

    public RepairLevel getRepairLevel() {
        return repairLevel;
    }

    public BigDecimal getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(BigDecimal repairCost) {
        this.repairCost = repairCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceInfo priceInfo = (PriceInfo) o;

        if (id != priceInfo.id) return false;
        if (device != priceInfo.device) return false;
        if (repairLevel != priceInfo.repairLevel) return false;
        return repairCost.equals(priceInfo.repairCost);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (device ^ (device >>> 32));
        result = 31 * result + repairLevel.hashCode();
        result = 31 * result + repairCost.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PriceInfo{");
        sb.append("id=").append(id);
        sb.append(", device=").append(device);
        sb.append(", repairLevel=").append(repairLevel);
        sb.append(", repairCost=").append(repairCost);
        sb.append('}');
        return sb.toString();
    }

}
