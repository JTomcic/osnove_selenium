package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercise3 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php");
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            driver.findElement(By.className("add-new")).click();
            driver.findElement(By.name("name")).sendKeys("Jovana Tomcic");
            driver.findElement(By.name("department")).sendKeys("Pedagogy");
            driver.findElement(By.name("phone")).sendKeys("545454545454");
            driver.findElement(By.xpath("//tbody/tr[" + (i + 4) + "]/td[4]/a[1]")).click();
            Thread.sleep(500);
        }


    }
}
