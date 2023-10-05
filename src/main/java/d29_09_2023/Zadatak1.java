package d29_09_2023;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class Zadatak1 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");

        WebElement delaySelectElement = driver.findElement(By.id("delay-select"));
        Select delaySelect = new Select(delaySelectElement);
        delaySelect.selectByValue("2000");

        new Actions(driver).scrollToElement(driver.findElement(By.xpath("//div[text()='C']")))
                .perform();
        new Actions(driver).scrollToElement(driver.findElement(By.id("infinite-scroll-button")))
                .perform();

        List<WebElement> element = driver.findElements(By.cssSelector("div.item"));
        wait
                .until(ExpectedConditions.elementToBeClickable(
                        By.id("infinite-scroll-button")))
                .click();
        wait
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath("//*[@id='infinite-scroll-container']/div[2]"), element.size() + 3));
        wait
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@disabled]")));

        driver.quit();
    }
}


