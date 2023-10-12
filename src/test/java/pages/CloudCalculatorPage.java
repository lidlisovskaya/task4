package pages;

import model.PricingCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class CloudCalculatorPage extends BasePage {
    private final Logger log = LogManager.getRootLogger();
    private static final String DROPDOWN_OPTION_XPATH_PATTERN = "//*[contains(@class, 'md-clickable')]//md-option/div[contains(text(), '%s')]";
    private final static String INNER_FRAME_NAME = "myFrame";
    private final static String NUMBERS_OF_INSTANCES_XPATH = "//input[@type='number' and @name='quantity']";
    private final static String TOTAL_PRICE_XPATH = "//h2/b[@class='ng-binding']";

    @FindBy(xpath = "//iframe[contains(@src,'https://cloud.google.com/frame/products/calculator')]")
    private WebElement outerFrame;

    @FindBy(xpath = "//div[@title='Compute Engine']")
    private WebElement computeEngineSection;

    @FindBy(xpath = NUMBERS_OF_INSTANCES_XPATH)
    private List<WebElement> numberOfInstancesSelector;

    @FindBy(xpath = "//md-select[contains(@ng-model,'computeServer') and contains(@aria-label,'Operating System')]")
    private WebElement softwareSelector;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    private WebElement provisioningModelSelector;

    @FindBy(xpath = "//md-select[@placeholder='Series']")
    private WebElement seriesSelector;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement machineTypeSelector;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGpusCheckbox;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement gpuTypeSelector;


    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGpusSelector;

    @FindBy(xpath = "//md-select[@placeholder='Local SSD' and contains(@class, 'ng-pristine')]")
    private WebElement localSsdSelector;


    @FindBy(xpath = "//md-select[@placeholder='Datacenter location' and @md-container-class='cpc-region-select']")
    private WebElement datacenterLocationSelector;


    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    private WebElement committedUsageSelector;


    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(text(),'Add to Estimate')]")
    private WebElement addToEstimate;

    @FindBy(xpath = TOTAL_PRICE_XPATH)
    private WebElement totalPrice;

    @FindBy(id = "Email Estimate")
    private WebElement emailEstimate;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailAddressContainer;

    @FindBy(xpath = "//button[contains(text(),'Send Email')]")
    private WebElement sendEmailButton;

    public CloudCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        switchToActiveFrame();
    }

    public CloudCalculatorPage chooseSection() {
        clickThis(computeEngineSection);
        log.info("Section chosen");
        return this;
    }

    public CloudCalculatorPage enterNumberOfInstances(PricingCalculator calculator) {
        numberOfInstancesSelector.get(0).sendKeys(calculator.getNumberOfInstances());
        log.info("Number of instances added");
        return this;
    }

    public CloudCalculatorPage selectOS(PricingCalculator calculator) {
        clickThis(softwareSelector);
        selectDropDownOption(calculator.getOperatingSystem());

        log.info("OS selected");
        return this;
    }

    public CloudCalculatorPage selectProvisioningModel(PricingCalculator calculator) {
        clickThis(provisioningModelSelector);
        selectDropDownOption(calculator.getModel());
        log.info("Provision Model selected");
        return this;
    }

    public CloudCalculatorPage selectSeries(PricingCalculator calculator) {
        clickThis(seriesSelector);
        selectDropDownOption(calculator.getSeries());
        log.info("Series selected");
        return this;
    }

    public CloudCalculatorPage selectMachineType(PricingCalculator calculator) {
        clickThis(machineTypeSelector);
        selectDropDownOption(calculator.getMachineType());
        log.info("Machine type selected");
        return this;
    }

    public CloudCalculatorPage clickAddGpuCheckbox() {
        clickThis(addGpusCheckbox);
        log.info("DPU added");
        return this;
    }

    public CloudCalculatorPage selectGpuType(PricingCalculator calculator) {
        clickThis(gpuTypeSelector);
        selectDropDownOption(calculator.getGpuType());
        log.info("GPU type selected");
        return this;
    }

    public CloudCalculatorPage selectNumberOfGpu(PricingCalculator calculator) {
        clickThis(numberOfGpusSelector);
        selectDropDownOption(calculator.getNumberOfGPUs());
        log.info("Number of GPU selected");
        return this;
    }

    public CloudCalculatorPage selectSSD(PricingCalculator calculator) {
        clickThis(localSsdSelector);
        selectDropDownOption(calculator.getSsd());
        log.info("SSD selected");
        return this;
    }

    public CloudCalculatorPage selectDataCenterLocation(PricingCalculator calculator) {
        clickThis(datacenterLocationSelector);
        selectDropDownOption(calculator.getDataCenterLocation());
        log.info("Data Center Location selected");
        return this;
    }

    public CloudCalculatorPage selectCommittedUsage(PricingCalculator calculator) {
        clickThis(committedUsageSelector);
        selectDropDownOption(calculator.getCommittedUsage());
        log.info("Committed usage selected");
        return this;
    }

    public String calculateTotalPrice() {
        clickThis(addToEstimate);
        waitTillElementIsPresent(By.xpath(TOTAL_PRICE_XPATH));
        log.info("Price is calculated");
        return totalPrice.getText();
    }

    public void sendCalculatedInfoToEmail(String emailAddress) {
        clickThis(emailEstimate);
        emailAddressContainer.sendKeys(emailAddress);
        clickThis(sendEmailButton);
        log.info("Price is sent to email");
    }


    public CloudCalculatorPage switchToCalculatorPage() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.switchTo().defaultContent();
        switchToActiveFrame();
        log.info("Switch to ClaudCalculatorPage");
        return this;
    }

    private void switchToActiveFrame() {
        driver.switchTo().frame(outerFrame.getAttribute("name"));
        driver.switchTo().frame(INNER_FRAME_NAME);
        log.info("Switch to main form frame");
    }
}
