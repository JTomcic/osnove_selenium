package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends BasicPage {
    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public int getAddedCartItems() {
        return driver.findElements(By.className("cart_item")).size();
    }

}
