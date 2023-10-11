package pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CloudSearchResultsPage extends BasePage {
    private final Logger log = LogManager.getRootLogger();
    @FindBy(xpath = "//div[@class='gs-title']//b[contains(text(),'Google Cloud Pricing Calculator')]")
    private WebElement calculatorLink;

    public CloudSearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CloudCalculatorPage clickMatchingResult() {
        calculatorLink.click();
        log.info("Matching result found");
        return new CloudCalculatorPage(driver);
    }
}
