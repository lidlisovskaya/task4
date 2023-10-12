package service;

import model.PricingCalculator;

public class PricingCalculatorCreator {
    public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.numberOfInstances";
    public static final String TESTDATA_OPERATING_SYSTEM = "testdata.operatingSystem";
    public static final String TESTDATA_MODEL = "testdata.model";
    public static final String TESTDATA_SERIES = "testdata.series";
    public static final String TESTDATA_MACHINE_TYPE = "testdata.machineType";
    public static final String TESTDATA_GPU_TYPE = "testdata.gpuType";
    public static final String TESTDATA_NUMBER_OF_GPUS = "testdata.numberOfGPUs";
    public static final String TESTDATA_SSD = "testdata.ssd";
    public static final String TESTDATA_DATA_CENTER_LOCATION = "testdata.dataCenterLocation";
    public static final String TESTDATA_COMMITTED_USAGE = "testdata.committedUsage";

    public static PricingCalculator createWithCredentialsFromProperty() {
        return new PricingCalculator(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES),
                TestDataReader.getTestData(TESTDATA_OPERATING_SYSTEM),
                TestDataReader.getTestData(TESTDATA_MODEL),
                TestDataReader.getTestData(TESTDATA_SERIES),
                TestDataReader.getTestData(TESTDATA_MACHINE_TYPE),
                TestDataReader.getTestData(TESTDATA_GPU_TYPE),
                TestDataReader.getTestData(TESTDATA_NUMBER_OF_GPUS),
                TestDataReader.getTestData(TESTDATA_SSD),
                TestDataReader.getTestData(TESTDATA_DATA_CENTER_LOCATION),
                TestDataReader.getTestData(TESTDATA_COMMITTED_USAGE));
    }
}
