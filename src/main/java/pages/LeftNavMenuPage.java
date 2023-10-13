package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

    public List<WebElement> getLeftNavMenuItems() {
        return driver.findElements(By.cssSelector("nav a"));
    }

    public int getNumberOfLeftNavMenuItems() {
        return getLeftNavMenuItems().size();
    }

    public String getSingleLeftNavMenuItemValue(int itemIndex) {
        return getLeftNavMenuItems().get(itemIndex).getText();
    }

    public void clickOnLeftNavMenuItem(int itemIndex) {
        getLeftNavMenuItems().get(itemIndex).click();
    }

    public boolean doesResetAppButtonExist() {
        return elementExist(By.linkText("Reset App State"), 0);
    }
    public WebElement getEkisButton() {
        return driver.findElement(By.id("react-burger-cross-btn"));
    }
    public void clickEkisButton() {
        getEkisButton().click();
    }
    public boolean doesEkisButtonExist() {
        return elementExist(By.id("react-burger-cross-btn"));
    }
}
