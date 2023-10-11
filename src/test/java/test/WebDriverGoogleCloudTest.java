package test;

import org.testng.annotations.*;
import model.PricingCalculator;
import pages.CloudCalculatorPage;
import pages.CloudHomePage;
import service.PricingCalculatorCreator;


public class WebDriverGoogleCloudTest extends CommonConditions {

    @Test
    public void isTotalPriceMatchingIsCalculated() {
        PricingCalculator calculator = PricingCalculatorCreator.withCredentialsFromProperty();
        CloudCalculatorPage cloudCalculatorPage = new CloudHomePage(driver)
                .openPage()
                .searchForTerms()
                .clickMatchingResult()
                .chooseSection()
                .numberOfInstancesEnter(calculator)
                .selectOS(calculator)
                .provisioningModelSelection(calculator)
                .seriesSelection(calculator)
                .machineType(calculator)
                .addGpuCheckboxclick()
                .addGpuType(calculator)
                .numberOfGpu(calculator)
                .selectSSD(calculator)
                .selectDataCenterLocation(calculator)
                .committedUsage(calculator);
        String priceMessageFromCalculatorPage = cloudCalculatorPage.calculate();
        softAssertions.assertTrue(priceMessageFromCalculatorPage.contains("USD"));
    }
}


