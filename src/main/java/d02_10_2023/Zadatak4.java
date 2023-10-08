package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.Duration;
import java.util.List;

public class Zadatak4 {
    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://itbootcamp.rs/");

        Actions actions = new Actions(driver);
        actions
                .scrollToElement(driver.findElement(By.className("owl-stage")))
                .perform();

        List<WebElement> links = driver.findElements(
                By.xpath("//div[@class = 'carousel-item']/img"));
        for (int i = 0; i < links.size(); i++) {
            String url = links.get(i).getAttribute("src");

            if (getHTTPResponseStatusCode(url) >= 200 && getHTTPResponseStatusCode(url) <  300) {
                downloadUsingNIO(url, "itbootcamp_slider/pic" + i + ".jpg");
            }
        }
    }
    public static int getHTTPResponseStatusCode(String u) throws IOException {
        URL url = new URL(u);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        return http.getResponseCode();
    }
    public static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }


}
