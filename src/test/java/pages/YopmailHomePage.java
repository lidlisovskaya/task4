package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class YopmailHomePage extends BasePage {
    private final Logger log = LogManager.getRootLogger();

    private final static String HOMEPAGE_URL = "https://yopmail.com/";
    private final static String MAIL_ID = "message";
    private final static String GENERATED_EMAIL_ADDRESS = "//div[@id='geny']//script//ancestor::span";
    private final static String INTERRUPTION_POPUP_CLOSE_BUTTON = "//div[@id='dismiss-button']";
    @FindBy(xpath = GENERATED_EMAIL_ADDRESS)
    private WebElement generatedEmailAddress;

    private List<String> tabs;


    @FindBy(xpath = "//*[@id='listeliens']/a[@href='email-generator']")
    private WebElement chooseRandomEmailGenerator;

    @FindBy(xpath = "//span[text()='Check Inbox']")
    private WebElement checkInboxButton;

    @FindBy(id = MAIL_ID)
    private WebElement mail;

    @FindBy(xpath = "//h2")
    private WebElement totalCostFromEmail;

    @FindBy(id = "refresh")
    private WebElement refreshMailButton;

    @FindBy(xpath = "//div[@id='dismiss-button']")
    private WebElement closeInterruptionBanner;


    public YopmailHomePage(WebDriver driver) {
        super(driver);
    }

    public YopmailHomePage openYopmailInNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(HOMEPAGE_URL);
        tabs = new ArrayList<>(driver.getWindowHandles());
        return this;
    }

    public String generateRandomEmailAddress() {
        clickThis(chooseRandomEmailGenerator);
        try {
            waitTillElementIsPresent(By.xpath(INTERRUPTION_POPUP_CLOSE_BUTTON));
            clickThis(closeInterruptionBanner);
        } catch (Exception e) {
            e.getSuppressed();
        }
        waitTillElementIsPresent(By.xpath(GENERATED_EMAIL_ADDRESS));
        log.info("Email address is generated and copied");
        return generatedEmailAddress.getText() + "@yopmail.com";
    }


    public String receiveEstimatedInfoFromGeneratedEmail() {
        pageScroll();
        clickThis(checkInboxButton);
        return getMailContent();
    }

    public YopmailHomePage switchToYopmailPage() {
        driver.switchTo().window(tabs.get(1));
        driver.switchTo().defaultContent();
        log.info("Switch to email generator page");
        return this;
    }

    private String getMailContent() {
        waitTillElementIsPresent(By.id(MAIL_ID));
        while (mail.getText().equals("This inbox is empty")) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            clickThis(refreshMailButton);
        }
        waitTillElementIsPresent(By.id(MAIL_ID));
        driver.switchTo().frame("ifmail");
        log.info("Price from email is taken");
        return totalCostFromEmail.getText();
    }
}

