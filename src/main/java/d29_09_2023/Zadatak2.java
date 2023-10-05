package d29_09_2023;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak2 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://docs.katalon.com/");

        driver.manage().window().maximize();

        WebElement htmlElement = driver.findElement(By.xpath("/html"));
        String currentTheme = htmlElement.getAttribute("data-theme");
        System.out.println("Current theme: " + currentTheme);

        if (currentTheme.equals("light")) {
            System.out.println("Current theme is light.");
        } else {
            System.out.println("Current theme is not light.");
        }

        driver
                .findElement(By.cssSelector(".toggleButton_rCf9")).click();

        wait
                .until(ExpectedConditions.attributeToBe(htmlElement, "data-theme", "dark"));

        String switchedTheme = htmlElement.getAttribute("data-theme");
        System.out.println("Switched theme: " + switchedTheme);

        Actions actions = new Actions(driver);
        actions
                .keyDown(Keys.CONTROL)
                .sendKeys("k")
                .keyUp(Keys.CONTROL)
                .perform();

        WebElement searchDialog = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.DocSearch-Modal")));

        WebElement searchInput = searchDialog.findElement(By.tagName("input"));
        String searchInputType = searchInput.getAttribute("type");
        System.out.println("The value of the type attribute for the search input: " + searchInputType);

        driver.quit();
    }
}



