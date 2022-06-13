package by.epam.jwdsc.entity;

import java.math.BigDecimal;

/**
 * The type Price info.
 */
public class PriceInfo extends CommonEntity {

    private long id;
    private long device;
    private RepairLevel repairLevel;
    private BigDecimal repairCost;


    /**
     * Instantiates a new Price info.
     *
     * @param id          the id
     * @param device      the device
     * @param repairLevel the repair level
     * @param repairCost  the repair cost
     */
    public PriceInfo(long id, long device, RepairLevel repairLevel, BigDecimal repairCost) {
        this.id = id;
        this.device = device;
        this.repairLevel = repairLevel;
        this.repairCost = repairCost;
    }

    /**
     * Instantiates a new Price info.
     *
     * @param device      the device
     * @param repairLevel the repair level
     * @param repairCost  the repair cost
     */
    public PriceInfo(long device, RepairLevel repairLevel, BigDecimal repairCost) {
        this.device = device;
        this.repairLevel = repairLevel;
        this.repairCost = repairCost;
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
     * Gets device.
     *
     * @return the device
     */
    public long getDevice() {
        return device;
    }

    /**
     * Gets repair level.
     *
     * @return the repair level
     */
    public RepairLevel getRepairLevel() {
        return repairLevel;
    }

    /**
     * Gets repair cost.
     *
     * @return the repair cost
     */
    public BigDecimal getRepairCost() {
        return repairCost;
    }

    /**
     * Sets repair cost.
     *
     * @param repairCost the repair cost
     */
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
