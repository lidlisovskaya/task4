package model;

public class PricingCalculator {
    private String numberOfInstances;
    private static String operatingSystem;
    private static String model;
    private String series;
    private String machineType;
    private String gpuType;
    private String numberOfGPUs;
    private String ssd;
    private String dataCenterLocation;
    private String committedUsage;

    public PricingCalculator(String numberOfInstances, String operatingSystem, String model, String series,
                             String machineType, String gpuType, String numberOfGPUs,
                             String ssd, String location, String committedUsage) {
        this.numberOfInstances = numberOfInstances;
        this.operatingSystem = operatingSystem;
        this.model = model;
        this.series = series;
        this.machineType = machineType;
        this.gpuType = gpuType;
        this.numberOfGPUs = numberOfGPUs;
        this.ssd = ssd;
        this.dataCenterLocation = location;
        this.committedUsage = committedUsage;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public  String getOperatingSystem() {
        return operatingSystem;
    }

    public  String getModel() {
        return model;
    }

    public String getSeries() {
        return series;
    }

    public String getMachineType() {
        return machineType;
    }

    public String getGpuType() {
        return gpuType;
    }

    public String getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public String getSsd() {
        return ssd;
    }

    public String getDataCenterLocation() {
        return dataCenterLocation;
    }

    public String getCommittedUsage() {
        return committedUsage;
    }

    public void setNumberOfInstances() {
        this.numberOfInstances = numberOfInstances;
    }

    public void setOperatingSystem() {
        this.operatingSystem = operatingSystem;
    }

    public void setModel() {
        this.model = model;
    }

    public void setSeries() {
        this.series = series;
    }

    public void setMachineType() {
        this.machineType = machineType;
    }

    public void setGpuType() {
        this.gpuType = gpuType;
    }

    public void setNumberOfGPUs() {
        this.numberOfGPUs = numberOfGPUs;
    }

    public void setSsd() {
        this.ssd = ssd;
    }

    public void setDataCenterLocation() {
        this.dataCenterLocation = dataCenterLocation;
    }

    public void setCommittedUsage() {
        this.committedUsage = committedUsage;
    }
}