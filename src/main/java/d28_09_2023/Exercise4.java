package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Exercise4 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("http://seleniumdemo.com/?post_type=product");
        driver.findElement(By.xpath("//*[@id='tc-page-wrap']/header/div[1]/div/div/div[2]/ul/li[1]/a"))
                .click();
        driver.findElement(By.xpath("//*[@id='s-651eab5a63373']"))
                .clear();
        driver.findElement(By.xpath("//*[@id='s-651eab5a63373']"))
                .sendKeys("BDD Cucumber");
        driver.findElement(By.xpath("//*[@id='s-651eab5a63373']"))
                .sendKeys(Keys.ENTER);

        WebElement firstSearchResult = driver. findElement(By.xpath("//*[@id='post-29']"));
        firstSearchResult.findElement(By.xpath("//a[@class='czr-title']"));
        if (firstSearchResult.getText().contains("BDD Cucumber")){
            System.out.println("The product name contains the search text.");
        } else {
            System.out.println("Product name doesn't contain search text.");
        }
        driver.quit();
    }
}

