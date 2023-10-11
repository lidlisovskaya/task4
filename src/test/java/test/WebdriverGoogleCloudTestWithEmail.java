package test;

import org.testng.annotations.*;
import model.PricingCalculator;
import pages.CloudCalculatorPage;
import pages.CloudHomePage;
import pages.YopmailHomePage;
import service.PricingCalculatorCreator;

public class WebdriverGoogleCloudTestWithEmail extends CommonConditions {
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
        YopmailHomePage yopmailHomePage = new YopmailHomePage(driver)
                .openYopmailInNewTab();
        String emailAddress = yopmailHomePage.generateRandomEmailAddress();


        cloudCalculatorPage.switchToCalculatorPage()
                .sendCalculatedInfoToEmail(emailAddress);
        yopmailHomePage.switchToYopmailPage();
        String priceMessageFromEmail = yopmailHomePage.receiveEstimatedInfoFromGeneratedEmail();
        softAssertions.assertEquals(priceMessageFromCalculatorPage, priceMessageFromEmail);
    }
}
