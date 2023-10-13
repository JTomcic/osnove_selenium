package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import retry.SwagLabsRetry;



public class SwagLabTests extends BasicTestSwag {
    @Test(priority = 1, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUsernameIsMissing() {
        loginPage.clickOnLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessageText(),
                "Epic sadface: Username is required",
                "Error message is not valid when username is missing");
    }

    @Test(priority = 2, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenPasswordIsMissing() {
        String username = "standard_user";

        loginPage.clearAndTypeUsername(username);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(
                loginPage.getErrorMessageText(),
                "Epic sadface: Password is required",
                "Error message is not valid when password is missing");

    }


    @Test(priority = 3, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenCredentialsAreWrong() {
        String username = "standard_user";
        String password = "invalidpassword";
        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessageText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message is not valid when credentials are wrong.");
    }


    @Test(priority = 4, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUserIsLocked() {
        String username = "locked_out_user";
        String password = "secret_sauce";
        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessageText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Error message is not valid when user is locked out.");
    }
    @Test(priority = 5, retryAnalyzer = SwagLabsRetry.class)
    public void verifySuccessfulLogin() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();



        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to inventory page after login.");

        topNavMenuPage.clickOnMenuButton();

        leftNavMenuPage.waitForMenuToBeVisible();

        Assert.assertTrue(leftNavMenuPage.doesLogoutButtonExist(),
                "Logout link should exists on menu.");

        leftNavMenuPage.clickOnLogoutLink();

        Assert.assertTrue(
                loginPage.doesUsernameInputExist(),
                "Should be redirected to login page after logout.");


    }

    @Test(priority = 6, retryAnalyzer = SwagLabsRetry.class)
    public void addingProductsToCart() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to inventory page after login.");

        inventoryPage.scrollToItem();

        inventoryPage.clickOnAddCartButton();
        String addedCartItemsAfterClick = inventoryPage.getCartBadgeNumber();

        Assert.assertTrue(
                inventoryPage.doesRemoveBtnExist(),
                "Remove button should be exist after click 'Add to cart' button.");

        Assert.assertEquals(addedCartItemsAfterClick, "1",
                "Cart elements indicator should be 1.");
    }

    @Test(priority = 7, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheUrl() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to inventory page after login.");

        inventoryPage.clickOnCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");
    }
    @Test(priority = 8, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheTitlePage() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to inventory page after login.");

        inventoryPage.scrollToItem();
        inventoryPage.clickOnAddCartButton();

        inventoryPage.clickOnCartButton();

        Assert.assertEquals(
                driver.getTitle(),
                        "Swag Labs",
                "After click on cart button the title should be 'Swag Labs'.");
    }
    @Test(priority = 9, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheTitleInHeader() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.getHeaderTitleText();
        Assert.assertEquals(topNavMenuPage.getHeaderTitleText(),
                "Swag Labs",
                "After click on cart button the header title should be 'Swag Labs'.");
    }
    @Test(priority = 10, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheHamburgerMenuButtonIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        Assert.assertTrue(
                topNavMenuPage.doesMenuButtonExist(),
                "Hamburger menu button should be exist after click on cart button.");
    }
    @Test(priority = 11, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheCartIconIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        Assert.assertTrue(
                topNavMenuPage.doesCartIconExist(),
                "Cart icon should be presented.");
    }
    @Test(priority = 12, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheHamburgerMenuButtonIsEnabled() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.clickOnMenuButton();

        leftNavMenuPage.waitForMenuToBeVisible();
    }
    @Test(priority = 13, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheCartIconIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();
        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");
    }
    @Test(priority = 14, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheCartIconHasCorrectNumberOfAddedItems() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.scrollToItem();

        inventoryPage.clickOnAddCartButton();

        inventoryPage.clickOnCartButton();
        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");

        int cartBadgeNumber = Integer.parseInt(inventoryPage.getCartBadgeNumber());

        Assert.assertEquals(cartBadgeNumber, cartPage.getAddedCartItems(),
                "Number in the cart icon should be equiavalent to the total numbers of added items.");
    }
}
