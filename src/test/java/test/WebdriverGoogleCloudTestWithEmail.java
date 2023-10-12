package test;

import org.testng.annotations.*;
import model.PricingCalculator;
import pages.CloudCalculatorPage;
import pages.CloudHomePage;
import pages.YopmailHomePage;
import service.PricingCalculatorCreator;

public class WebdriverGoogleCloudTestWithEmail extends CommonConditions {
    @Test
    public void checkIsTotalPriceFromEmailTheSameIsTotalPrice() {
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
