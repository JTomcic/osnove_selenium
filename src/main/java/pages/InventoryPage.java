package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage extends BasicPage {
    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void scrollToItem(){
        new Actions(driver)
                .scrollToElement(driver.findElement(By.id("item_4_title_link")))
                .perform();
    }
    public WebElement getAddToCartButton() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }
    public void clickOnAddCartButton() {
        getAddToCartButton().click();
    }

    public boolean doesRemoveBtnExist() {
        return elementExist(By.id("remove-sauce-labs-backpack"));
    }
    public WebElement getCartBadge() {
        return driver.findElement(By.cssSelector(".shopping_cart_badge"));
    }
    public String getCartBadgeNumber() {
        return getCartBadge().getText();
    }

    public WebElement getCartButton() {
        return driver.findElement(By.className("shopping_cart_link"));
    }
    public void clickOnCartButton() {
        getCartButton().click();
    }
}
