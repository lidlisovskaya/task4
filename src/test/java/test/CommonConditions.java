package test;

import driver.DriverSingleton;
import model.PricingCalculator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import utils.TestListener;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    protected PricingCalculator pricingCalculator;
    protected static final String TERMS = "Google Cloud Pricing Calculator";
    SoftAssert softAssertions;

    @BeforeMethod()
    public void browserSetUp() {
        driver = DriverSingleton.getDriver();
        driver.manage().window().maximize();
        softAssertions = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void browserQuit() {
        DriverSingleton.closeDriver();
    }

}

