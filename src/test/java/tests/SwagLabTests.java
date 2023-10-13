package tests;

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

        cartPage.getHeaderTitleText();
        Assert.assertEquals(cartPage.getHeaderTitleText(),
                "Swag Labs",
                "After click on cart button the header title should be 'Swag Labs'.");
    }
}
