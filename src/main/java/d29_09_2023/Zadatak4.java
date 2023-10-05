package d29_09_2023;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Zadatak4 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://tus.io/demo.html");

        List<WebElement> link = driver.findElements(By.cssSelector("a[href]"));
        List<WebElement> h3Elements = driver.findElements(By.tagName("h3"));

        Actions actions = new Actions(driver);
        for (int i = 0; i < h3Elements.size(); i++) {
            try {
                actions.scrollToElement(h3Elements.get(i));
                actions.perform();
            } catch (Exception e) {
            }
            System.out.println(h3Elements.get(i).getText());
        }
        driver.quit();
    }
}

