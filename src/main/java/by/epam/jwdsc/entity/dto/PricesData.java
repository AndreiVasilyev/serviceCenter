package by.epam.jwdsc.entity.dto;

/**
 * The type Prices data.
 */
public class PricesData {
    private String device;
    private String diagnostic;
    private String maintenance;
    private String repairLevel1;
    private String repairLevel2;
    private String repairLevel3;
    private String technicalConclusion;

    /**
     * Gets device.
     *
     * @return the device
     */
    public String getDevice() {
        return device;
    }

    /**
     * Sets device.
     *
     * @param device the device
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * Gets diagnostic.
     *
     * @return the diagnostic
     */
    public String getDiagnostic() {
        return diagnostic;
    }

    /**
     * Sets diagnostic.
     *
     * @param diagnostic the diagnostic
     */
    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    /**
     * Gets maintenance.
     *
     * @return the maintenance
     */
    public String getMaintenance() {
        return maintenance;
    }

    /**
     * Sets maintenance.
     *
     * @param maintenance the maintenance
     */
    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    /**
     * Gets repair level 1.
     *
     * @return the repair level 1
     */
    public String getRepairLevel1() {
        return repairLevel1;
    }

    /**
     * Sets repair level 1.
     *
     * @param repairLevel1 the repair level 1
     */
    public void setRepairLevel1(String repairLevel1) {
        this.repairLevel1 = repairLevel1;
    }

    /**
     * Gets repair level 2.
     *
     * @return the repair level 2
     */
    public String getRepairLevel2() {
        return repairLevel2;
    }

    /**
     * Sets repair level 2.
     *
     * @param repairLevel2 the repair level 2
     */
    public void setRepairLevel2(String repairLevel2) {
        this.repairLevel2 = repairLevel2;
    }

    /**
     * Gets repair level 3.
     *
     * @return the repair level 3
     */
    public String getRepairLevel3() {
        return repairLevel3;
    }

    /**
     * Sets repair level 3.
     *
     * @param repairLevel3 the repair level 3
     */
    public void setRepairLevel3(String repairLevel3) {
        this.repairLevel3 = repairLevel3;
    }

    /**
     * Gets technical conclusion.
     *
     * @return the technical conclusion
     */
    public String getTechnicalConclusion() {
        return technicalConclusion;
    }

    /**
     * Sets technical conclusion.
     *
     * @param technicalConclusion the technical conclusion
     */
    public void setTechnicalConclusion(String technicalConclusion) {
        this.technicalConclusion = technicalConclusion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PricesData that = (PricesData) o;

        if (device != null ? !device.equals(that.device) : that.device != null) return false;
        if (diagnostic != null ? !diagnostic.equals(that.diagnostic) : that.diagnostic != null) return false;
        if (maintenance != null ? !maintenance.equals(that.maintenance) : that.maintenance != null) return false;
        if (repairLevel1 != null ? !repairLevel1.equals(that.repairLevel1) : that.repairLevel1 != null) return false;
        if (repairLevel2 != null ? !repairLevel2.equals(that.repairLevel2) : that.repairLevel2 != null) return false;
        if (repairLevel3 != null ? !repairLevel3.equals(that.repairLevel3) : that.repairLevel3 != null) return false;
        return technicalConclusion != null ? technicalConclusion.equals(that.technicalConclusion) : that.technicalConclusion == null;
    }

    @Override
    public int hashCode() {
        int result = device != null ? device.hashCode() : 0;
        result = 31 * result + (diagnostic != null ? diagnostic.hashCode() : 0);
        result = 31 * result + (maintenance != null ? maintenance.hashCode() : 0);
        result = 31 * result + (repairLevel1 != null ? repairLevel1.hashCode() : 0);
        result = 31 * result + (repairLevel2 != null ? repairLevel2.hashCode() : 0);
        result = 31 * result + (repairLevel3 != null ? repairLevel3.hashCode() : 0);
        result = 31 * result + (technicalConclusion != null ? technicalConclusion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PricesData{");
        sb.append("device='").append(device).append('\'');
        sb.append(", diagnostic='").append(diagnostic).append('\'');
        sb.append(", maintenance='").append(maintenance).append('\'');
        sb.append(", repairLevel1='").append(repairLevel1).append('\'');
        sb.append(", repairLevel2='").append(repairLevel2).append('\'');
        sb.append(", repairLevel3='").append(repairLevel3).append('\'');
        sb.append(", technicalConclusion='").append(technicalConclusion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
