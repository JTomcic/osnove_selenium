package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LeftNavMenuPage extends BasicPage{
    public LeftNavMenuPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void waitForMenuToBeVisible() {
        wait
                .withMessage("Left Menu navigation should be visible.")
                .until(ExpectedConditions
                .visibilityOfElementLocated(By.className("bm-menu-wrap")));
    }
    public boolean doesLogoutButtonExist() {
        return elementExist(By.linkText("Logout"), 0);
    }
    public WebElement getLogoutLink() {
        return driver.findElement(By.linkText("Logout"));
    }
    public void clickOnLogoutLink() {
        getLogoutLink().click();
    }

}
