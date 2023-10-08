package p05_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class SwagLabsTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://www.saucedemo.com/";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }
    @BeforeMethod
    public void home() {
        driver.manage().deleteAllCookies();
        driver.navigate().to(baseUrl);
    }
    @Test
    public void verifyErrorIsDisplayedWhenUsernameIsMissing() {
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("Error message is not displayed when username is missing.")
                .until(ExpectedConditions
                        .textToBePresentInElementLocated(
                                By.cssSelector(".error-message-container h3"),
                                "Username is required"));
    }
    @Test
    public void verifyErrorIsDisplayedWhenPasswordIsMissing() {
        String username = "standard_user";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("Error message is not displayed when password is missing.")
                .until(ExpectedConditions
                        .textToBePresentInElementLocated(
                                By.cssSelector(".error-message-container h3"),
                                "Password is required"));

    }
    @Test
    public void verifyErrorIsDisplayedWhenCredentialsAreWrong () {
        String username = "standard_user";
        String password = "invalidpassword";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("Error message is not displayed when password is missing.")
                .until(ExpectedConditions
                        .textToBePresentInElementLocated(
                                By.cssSelector(".error-message-container h3"),
                                "Username and password do not match any user in this service"));
    }
    @Test
    public void verifyErrorIsDisplayedWhenUserIsLocked() {
        String username = "locked_out_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("Error message didn't show after this user have been locked out.")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".error-message-container > h3"),
                        "Epic sadface: Sorry, this user has been locked out."));
    }
    @Test
    public void verifySuccessfulLogin() {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to inventory page after login.");

        driver.findElement(By.id("react-burger-menu-btn")).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("bm-menu-wrap")));

        boolean logoutExists =
                !driver.findElements(By.id("logout_sidebar_link")).isEmpty();

        Assert.assertTrue(logoutExists, "Logout should exists.");

        driver.findElement(By.id("logout_sidebar_link")).click();

        boolean loginFormExists =
                !driver.findElements(By.className("login_wrapper")).isEmpty();

        Assert.assertTrue(
                loginFormExists,
                "Should be redirected to login page after logout.");
    }
    @Test
    public void addingProductsToCart() {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to inventory page after login.");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack"))
                .click();

        wait
                .withMessage("Remove button didn't show.")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-sauce-labs-backpack")));
        Assert.assertEquals(driver.findElement(
                By.xpath("//*[@id='shopping_cart_container']/a/span")).getText(), "1");
    }
    @Test
    public void  viewingProductDetails() {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to inventory page after login.");

        driver.findElement(By.xpath("//*[@id='item_4_title_link']/div")).click();
        WebElement productDetail = driver.findElement(By.xpath("//*[@id='inventory_item_container']"));
        Assert.assertTrue(productDetail.getText().contains("Sauce Labs Backpack"));
        Assert.assertTrue(productDetail.getText().contains("Price: $29.99"));
        Assert.assertTrue(driver.findElement(
                By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).isDisplayed());
    }
    @Test
    public void removingProductsFromCart() {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to inventory page after login.");

        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).click();

        Assert.assertEquals(driver.findElement(
                By.xpath("//*[@id='shopping_cart_container']/a/span")).getText(), "1");

        driver.findElement(By.id("remove-sauce-labs-backpack"))
                .click();
        wait
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("inventory_item_name")));
    }
    @Test
    public void productCheckout() {
        String username = "standard_user";
        String password = "secret_sauce";
        String checkoutName = "Pera";
        String checkoutLastName = "Peric";
        String checkoutZip = "18000";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to inventory page after login.");

        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).click();

        Assert.assertEquals(driver.findElement(
                By.xpath("//*[@id='shopping_cart_container']/a/span")).getText(), "1");

        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys(checkoutName);
        driver.findElement(By.id("last-name")).sendKeys(checkoutLastName);
        driver.findElement(By.id("postal-code")).sendKeys(checkoutZip);
        driver.findElement(By.id("continue")).click();

        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(),
                "Sauce Labs Backpack",
                "Product name is wrong.");

        Assert.assertEquals(driver.findElement(By.className("inventory_item_price")).getText(), "$29.99",
                "Product price is wrong.");

        driver.findElement(By.id("finish")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#checkout_complete_container h2")).getText(),
                "Thank you for your order!",
                "Message 'Thank you for your order!' is not visible.");
    }
    @Test
    public void validateSocialLinksInFooter() {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to inventory page after login.");

        new Actions(driver)
                .scrollToElement(driver.findElement(By.className("social")))
                .perform();

        List<WebElement> socialLinks = driver.findElements(By.cssSelector(".social a"));
        for (int i = 0; i < socialLinks.size(); i++) {
            String url = socialLinks.get(i).getAttribute("href");

            try {
                Assert.assertEquals(getHTTPResponseStatusCode(url), 200,
                        "Status code for social links is not 200.");
            } catch (Exception e) {
            }
        }
    }
    public static int getHTTPResponseStatusCode(String u) throws IOException {
        URL url = new URL(u);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        return http.getResponseCode();
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
