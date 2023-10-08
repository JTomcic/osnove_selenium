package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://itbootcamp.rs/");
        Actions actions = new Actions(driver);

        WebElement vestiElement = driver.findElement(By.xpath("//*[@id='menu-item-6408']/a"));
        actions.moveToElement(vestiElement).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='menu-item-6408']")));

        WebElement kurseviElement = driver.findElement(By.xpath("//*[@id='menu-item-5362']/a"));
        actions.moveToElement(kurseviElement).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='menu-item-5362']/a")));

        WebElement prijavaElement = driver.findElement(By.xpath("//*[@id='menu-item-5453']/a"));
        actions.moveToElement(prijavaElement).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='menu-item-5453']/a")));

        Thread.sleep(1000);
        driver.quit();
    }
}
