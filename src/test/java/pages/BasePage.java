package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    protected final static int DEFAULT_WAIT_SECONDS = 10;
    private static final String DROPDOWN_OPTION_XPATH_PATTERN = "//*[contains(@class, 'md-clickable')]//md-option/div[contains(text(), '%s')]";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void clickThis(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS))
                .until(ExpectedConditions
                        .elementToBeClickable(element));
        element.click();
    }

    protected void waitTillElementIsPresent(By by) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS))
                .until(ExpectedConditions
                        .presenceOfElementLocated(by));
    }
    protected void pageScroll(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void selectDropDownOption(String option) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DROPDOWN_OPTION_XPATH_PATTERN, option))))
                .click();
    }
}
