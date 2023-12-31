package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
public class TopNavMenuPage extends BasicPage{
    public TopNavMenuPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public WebElement getMenuButton() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }
    public void clickOnMenuButton() {
        getMenuButton().click();
    }

    public boolean doesMenuButtonExist() {
        return elementExist(By.id("react-burger-menu-btn"));
    }
    public boolean doesCartIconExist() {
        return elementExist(By.id("shopping_cart_container"));
    }

    public boolean doesCartBadgeExist() {
        return elementExist(By.className("shopping_cart_badge"), 0);
    }

    public String getHeaderTitleText() {
        return driver.findElement(By.className("app_logo")).getText();
    }
    public String getSubheaderTitleText() {
        return driver.findElement(By.className("header_secondary_container")).getText();
    }

    public void clickOnShoppingCartButton() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
}
