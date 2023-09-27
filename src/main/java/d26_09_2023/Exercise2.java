package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Exercise2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://s.bootsnipp.com/iframe/Dq2X");
        List<WebElement> alerts = driver.findElements(By.cssSelector(".alert"));
        for (int i = 0; i < alerts.size(); i++) {
            driver.findElement(By.cssSelector(".alert:last-child > button")).click();
            if (driver.findElements(By.cssSelector(".alert")).size() == (alerts.size() - (i + 1))) {
                System.out.println("Uspesno obrisan alert.");
            }
            Thread.sleep(1000);
        }
    }
}
