package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    public boolean doesItemTitleExist() {
        return elementExist(By.id("item_4_title_link"));
    }

    public void clickOnItemTitle() {
        driver.findElement(By.id("item_4_title_link")).click();
    }

    public boolean doesDescriptionCartItemExist() {
        return elementExist(By.className("inventory_item_desc"));
    }
    public boolean doesPriceCartItemExist() {
        return elementExist(By.className("inventory_item_price"));
    }
    public boolean doesQuantityCartItemExist() {
        return elementExist(By.className("cart_quantity"));
    }
    public boolean doesRemoveCartBtnExist() {
        return elementExist(By.id("remove-sauce-labs-backpack"));
    }

    public void clickOnRemoveCartItemBtn() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    public void waitForCartItemBeInvisible() {
        wait
                .withMessage("Delete cart item should be invisible.")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("cart_item")));
    }

    public boolean doesContinueShoppingBtnExist() {
        return elementExist(By.id("continue-shopping"));
    }

    public void clickOnContinueShoppingBtn() {
        driver.findElement(By.id("continue-shopping")).click();
    }

    public boolean doesCheckoutBtnExist() {
        return elementExist(By.id("checkout"));
    }

    public void clickOnCheckoutBtn() {
        driver.findElement(By.id("checkout")).click();
    }

    public boolean doesTwitterBtnExist() {
        return elementExist(By.linkText("Twitter"));
    }

    public void clickOnTwitterBtn() {
        driver.findElement(By.linkText("Twitter")).click();
    }

    public boolean doesFacebookBtnExist() {
        return elementExist(By.linkText("Facebook"));
    }

    public void clickOnFacebookBtn() {
        driver.findElement(By.linkText("Facebook")).click();
    }

    public boolean doesLinkedinBtnExist() {
        return elementExist(By.linkText("LinkedIn"));
    }


    public void clickOnLinkedinBtn() {
        driver.findElement(By.linkText("LinkedIn")).click();
    }

}
