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
    public boolean doesLogoutButtonExist() {
        return elementExist(By.linkText("Logout"), 0);
    }
    public boolean doesMenuButtonExist() {
        return elementExist(By.id("react-burger-menu-btn"));
    }

    public String getHeaderTitleText() {
        return driver.findElement(By.className("app_logo")).getText();
    }

}
