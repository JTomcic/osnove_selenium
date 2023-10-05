package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
//1.Zadatak
//        Napisati program koji ucitava stranicu https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=
//        Klik na Type drawdown
//        Klik na Public iz drowdowna
//        Ceka da se Clear dugme u desnom uglu prikaze koristeci explicit wait
//        Kilk na Clear filter u desnom uglu
public class Exercise1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=");

        WebElement typeDrawDown = driver.findElement(By.xpath("//*[@id='type-options']/summary"));
        typeDrawDown.click();

        WebElement publicDrowDown = driver.findElement(By.xpath("//*[@id='type-options']/details-menu/div/div/label[2]"));
        publicDrowDown.click();

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(@class, 'issues-reset-query')]"))).click();

        driver.quit();
    }
}
