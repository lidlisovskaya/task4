package test;

import org.testng.annotations.*;
import model.PricingCalculator;
import pages.CloudCalculatorPage;
import pages.CloudHomePage;
import service.PricingCalculatorCreator;


public class WebDriverGoogleCloudTest extends CommonConditions {

    @Test
    public void checkIsTotalPriceMatchingIsCalculated() {
        PricingCalculator calculator = PricingCalculatorCreator.createWithCredentialsFromProperty();
        CloudCalculatorPage cloudCalculatorPage = new CloudHomePage(driver)
                .openPage()
                .searchForTerms()
                .clickMatchingResult()
                .chooseSection()
                .enterNumberOfInstances(calculator)
                .selectOS(calculator)
                .selectProvisioningModel(calculator)
                .selectSeries(calculator)
                .selectMachineType(calculator)
                .clickAddGpuCheckbox()
                .selectGpuType(calculator)
                .selectNumberOfGpu(calculator)
                .selectSSD(calculator)
                .selectDataCenterLocation(calculator)
                .selectCommittedUsage(calculator);
        String priceMessageFromCalculatorPage = cloudCalculatorPage.calculateTotalPrice();
        softAssertions.assertTrue(priceMessageFromCalculatorPage.contains("USD"));
    }
}


