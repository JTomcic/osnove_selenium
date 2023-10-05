package d03_10_2023;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BootstrapTableTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void baseURL() {
        driver.navigate().to("https://s.bootsnipp.com/iframe/K5yrx");
    }
    @Test
    public void editRow() {
        String firstName = "Bojana";
        String lastName = "Bojanic";
        String middleName = "Boka";

        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Expected title: Table with Edit and Update Data - Bootsnipp.com");

        driver.findElement(By.xpath("//*[@id='d1']/td[5]/button")).click();
        wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit']/div")));

        driver.findElement(By.xpath("//*[@id='fn']")).clear();
        driver.findElement(By.xpath("//*[@id='fn']")).sendKeys(firstName);

        driver.findElement(By.xpath("//*[@id='ln']")).clear();
        driver.findElement(By.xpath("//*[@id='ln']")).sendKeys(lastName);

        driver.findElement(By.xpath("//*[@id='mn']")).clear();
        driver.findElement(By.xpath("//*[@id='mn']")).sendKeys(middleName);

        driver.findElement(By.xpath("//*[@id='up']")).click();

        wait
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='edit']/div")));

        Assert.assertEquals(driver.findElement(
                By.id("f1")).getText(), firstName, "Expected: "+ firstName);
        Assert.assertEquals(driver.findElement(
                By.id("l1")).getText(), lastName, "Expected: "+ lastName);
        Assert.assertEquals(driver.findElement(
                By.id("m1")).getText(), middleName, "Expected: "+ middleName);
    }
    @Test
    public void deleteRow() {
        String firstName = "Bojana";
        String lastName = "Bojanic";
        String middleName = "Boka";

        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Expected title: Table with Edit and Update Data - Bootsnipp.com");

        driver.findElement(By.xpath("//*[@id='d1']/td[6]/button")).click();

        wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='delete']")));

        driver.findElement(By.xpath("//*[@id='del']")).click();

        wait
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='delete']")));

        List<WebElement> deleteRow = driver.findElements(By.xpath("//*[@id='d1']"));

        wait
                .withMessage("There's only one row!")
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath("//*[@id='d1']"),  deleteRow.size()));
    }
    @Test
    public void takeAScreenshot() throws IOException {
        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Expected title: Table with Edit and Update Data - Bootsnipp.com");

        File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(f, new File("C://Projekti//osnove_selenium//screenshots//screenshot2.jpg"));

    }
    @AfterClass
    public void close(){
        driver.quit();
    }
}
