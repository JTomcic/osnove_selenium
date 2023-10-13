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
    public String getItemTitleText() {
        return driver.findElement(By.id("item_4_title_link")).getText();
    }
    public boolean doesDescriptionCartItemExist() {
        return elementExist(By.className("inventory_item_desc"));
    }
    public boolean doesPriceCartItemExist() {
        return elementExist(By.className("inventory_item_price"));
    }

}
