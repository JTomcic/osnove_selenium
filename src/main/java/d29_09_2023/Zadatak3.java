package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");


        List<WebElement> button = driver.findElements(By.xpath("//button[contains(@id, 'basic-')]"));
        List<WebElement> toast = driver.findElements(By.xpath("//div[contains(@id, 'basic-')]"));

        for (int i = 0; i < 4; i++) {
            Thread.sleep(1000);
            button.get(i).click();
        }

        wait
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath("//div[contains(@class, 'toast-fixed show')]"), 4));
        System.out.println("Toast are visible");

        wait
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath("//div[contains(@class, 'toast-fixed show')]"),0));
        System.out.println("Toasts are invisible");


        driver.quit();
    }
}
