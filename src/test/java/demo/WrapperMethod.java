package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.dockerjava.api.model.Driver;

public class WrapperMethod {
    public static void advanceSendkeys(WebElement element, String text, ChromeDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }
public static WebElement advanceFindElement(By locator,ChromeDriver driver){
    WebElement element = driver.findElement(locator);
    return element;
}
    public static void advanceClick(WebElement element, ChromeDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
    }

    public static String advanceGettext(WebElement element, ChromeDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public static void scrollElement(WebElement element, ChromeDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
