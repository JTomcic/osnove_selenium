package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demoqa.com/broken");

        List<WebElement> links = driver.findElements(
                By.xpath("//*[@id='app']/div/div/div[2]/div[2]/div[2]/a[1]"));

        for (int i = 0; i < links.size(); i++) {
            String url = links.get(i).getAttribute("href");

            if (getHTTPResponseStatusCode(url) > 200 && getHTTPResponseStatusCode(url)< 400) {
                System.out.println("Status is greater than 200 and less than 400!");
            } else {
                System.out.println("Status is not greater than 200 and less than 400");
            }
        }
        driver.quit();
    }
    public static int getHTTPResponseStatusCode(String u) throws IOException {
        URL url = new URL(u);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        return http.getResponseCode();
    }
}
