package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercise1 {
    public static void main(String[] args) throws InterruptedException {
        String username = "Admin";
        String password = "admin123";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(1000);

        driver
                .findElement(By.xpath("//input[@name='username']"))
                .sendKeys(username);
        driver
                .findElement(By.xpath("//input[@name='password']"))
                .sendKeys(password);
        driver
                .findElement(By.xpath("//button[@type = 'submit']")).click();
        Thread.sleep(5000);
        driver
                .findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("Me");
        driver
                .findElement(By.xpath("//span[@class = 'oxd-text oxd-text--span oxd-main-menu-item--name']")).click();
        Thread.sleep(1000);
        driver
                .findElement(By.xpath("//span[@class = 'oxd-userdropdown-tab']")).click();
        driver
                .findElement(By.xpath("//a[text()='Logout']")).click();
        Thread.sleep(5000);
        driver.quit();

    }
}
