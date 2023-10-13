package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasicPage{
    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getHeaderTitleText() {
        return driver.findElement(By.className("app_logo")).getText();
    }
}
