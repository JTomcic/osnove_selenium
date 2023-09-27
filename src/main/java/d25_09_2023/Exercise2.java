package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Exercise2 {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> todo = new ArrayList<>();
        todo.add("Visit Paris");
        todo.add("Visit Prague");
        todo.add("Visit London");
        todo.add("Visit New York");
        todo.add("Visit Belgrade");
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://example.cypress.io/todo");
        Thread.sleep(1000);
        WebElement inputNewToDo = driver.findElement(By.xpath("//input[@class='new-todo']"));
        for (String list : todo) {
            inputNewToDo.clear();
            inputNewToDo.sendKeys(list);
            inputNewToDo.sendKeys(Keys.ENTER);
            Thread.sleep(500);
        }

        for (int i = 0; i < (todo.size() + 2); i++) {
            driver.findElement(By.cssSelector(".todo-list > li:nth-child(" + (i + 1) + ") > div > input")).click();
        }
        Thread.sleep(5000);
        driver.quit();
    }
}
