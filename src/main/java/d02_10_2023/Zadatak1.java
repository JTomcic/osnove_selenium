package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id='active-face']/img"))
                .click();
        wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("image-option-remove")));
        driver.findElement(By.id("image-option-remove"))
                .click();

        ArrayList<File> images = new ArrayList<>();
        images.add(new File("test_data/front.jpg"));
        images.add(new File("test_data/left.jpg"));
        images.add((new File("test_data/right.jpg")));
        images.add(new File("test_data/back.jpg"));

        for (int i = 0; i < images.size(); i++) {
            driver.findElement(By.cssSelector("img.edit-image"))
                    .click();

            wait
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id='root;]/div[1]/div/div[2]/div/div[1]/label")));

            driver.findElement(By.xpath("//*[@id='imageUpload']"))
                    .sendKeys(images.get(i).getAbsolutePath());

            wait
                    .until(ExpectedConditions.numberOfElementsToBe(
                            By.xpath("//*[@id='root']/div[1]/div/div[2]/div/div[2]/div[1]")
                            , i + 1));

            driver.findElement(By.id("image-option-0"))
                    .click();
            driver.findElement(By.xpath("//*[@id='image-crop-done-button']"))
                    .click();
            Thread.sleep(2000);
        }

        wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("next-button"))).click();

        driver.findElement(By.id("textareaID")).sendKeys("Hello!");
        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("next-button")).click();
        Thread.sleep(5000);

        driver.quit();
    }
}
