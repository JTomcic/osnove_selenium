package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercise4 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://artplayer.org/");

        Thread.sleep(4000);
        driver.
                findElement(By.xpath("//i[@aria-label = 'Play']")).click();

//        driver.
//                findElement(By.xpath("//i[@style = 'display: none']")).click();
        Thread.sleep(3000);
        driver.
                findElement(By.xpath("//div[@aria-label = 'Screenshot']")).click();
        driver.
                findElement(By.xpath("//div[@aria-label = 'PIP Mode']")).click();
        Thread.sleep(1000);
        driver.
                findElement(By.xpath("//div[@aria-label = 'Exit PIP Mode']")).click();
        driver.
                findElement(By.xpath("//div[@aria-label = 'Web Fullscreen']")).click();
        driver.
                findElement(By.xpath("//div[@aria-label = 'Exit Fullscreen']")).click();
        Thread.sleep(3000);
        driver.quit();



    }
}
