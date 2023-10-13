package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasicPage {
    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public int getNumberOfAddedCartItems() {
        return driver.findElements(By.className("cart_item")).size();
    }

    public boolean doesAddedCartItemExist() {
        return elementExist(By.className("cart_item"));
    }

}
