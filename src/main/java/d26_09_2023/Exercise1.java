package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) throws InterruptedException {
        Scanner s = new Scanner(System.in);
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/automation-practice-form");
        System.out.println("Enter First name: ");
        driver.findElement(By.xpath("//input[@id = 'firstName']")).sendKeys(s.next());
        System.out.println("Enter Last name: ");
        driver.findElement(By.xpath("//input[@id = 'lastName']")).sendKeys(s.next());
        System.out.println("Enter Email: ");
        driver.findElement(By.xpath("//input[@id = 'userEmail']")).sendKeys(s.next());
        System.out.println("Enter Gender example:Male, Female or Other: ");
        if (s.next().equals("Male")) {
            driver.findElement(By.xpath("//label[@for = 'gender-radio-1']")).click();
        } else if (s.next().equals("Female")) {
            driver.findElement(By.xpath("//label[@for = 'gender-radio-2']")).click();
        } else {
            driver.findElement(By.xpath("//label[@for = 'gender-radio-3']")).click();
        }
        System.out.println("Enter Mobile number: ");
        driver.findElement(By.xpath("//input[@placeholder = 'Mobile Number']")).sendKeys(s.next());
        System.out.println("Enter Hobbies: ");
        if (s.next().equals("Sport")) {
            driver.findElement(By.xpath("//label[@for = 'hobbies-checkbox-1']")).click();
        } else if (s.next().equals("Reading")) {
            driver.findElement(By.xpath("//label[@for = 'hobbies-checkbox-2']")).click();
        } else if (s.next().equals("Music")){
            driver.findElement(By.xpath("//label[@for = 'hobbies-checkbox-3']")).click();
        }
        driver.findElement(By.xpath("//button[@id = 'submit']")).click();

    }
}
