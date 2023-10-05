package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Exercise5 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://seleniumdemo.com/?product=bdd-cucumber");
        new Actions(driver)
                .moveToElement(driver.findElement(
                        By.xpath("//*[@id='tc-page-wrap']/header/div[1]/div/div/div[2]/ul/li[2]/a")))
                .click()
                .perform();

        wait
                .until(ExpectedConditions.titleContains("Cart – Selenium Demo Page"));

        WebElement cartStatus = wait
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id='page-5']/div/section/div/div/p[1]")));

        String cartStatusText = cartStatus.getText();
        if (cartStatusText.equals("Your cart is currently empty.")) {
            System.out.println("Cart status is correct: " + cartStatusText);
        } else {
            System.out.println("Cart status is incorrect. Expected: Your cart is currently empty.");
        }
        driver.quit();
    }
}
