package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import retry.SwagLabsRetry;

import java.util.ArrayList;
import java.util.List;


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

        Assert.assertEquals(cartBadgeNumber, cartPage.getNumberOfAddedCartItems(),
                "Number in the cart icon should be equiavalent to the total numbers of added items.");
    }

    @Test(priority = 15, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheSubheaderTitle() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.getSubheaderTitleText();
        Assert.assertEquals(topNavMenuPage.getSubheaderTitleText(),
                "Your Cart",
                "After click on cart button the sub-header title should be 'Your Cart'.");
    }

    @Test(priority = 16, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheTotalNumberOfMenuOptions() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.clickOnMenuButton();

        Assert.assertEquals(leftNavMenuPage.getNumberOfLeftNavMenuItems(), 4,
                "There should be four total options in menu.");

    }

    @Test(priority = 17, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheSpellingOfAllOptionsInMenu() {
        String username = "standard_user";
        String password = "secret_sauce";
        List<String> expectedLeftNavMenuItems = new ArrayList<>();
        expectedLeftNavMenuItems.add("All Items");
        expectedLeftNavMenuItems.add("About");
        expectedLeftNavMenuItems.add("Logout");
        expectedLeftNavMenuItems.add("Reset App State");

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.clickOnMenuButton();

        for (int i = 0; i < leftNavMenuPage.getNumberOfLeftNavMenuItems(); i++) {
            Assert.assertEquals(leftNavMenuPage.getSingleLeftNavMenuItemValue(i),
                    expectedLeftNavMenuItems.get(i),
                    "Items value should be " + expectedLeftNavMenuItems.get(i) + ".");
        }
    }

    @Test(priority = 18, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfAllItemsOptionIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.clickOnMenuButton();

        leftNavMenuPage.clickOnLeftNavMenuItem(0);

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/inventory.html",
                "Should be redirected to products page after click on All items link.");
    }

    @Test(priority = 19, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfAboutOptionIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.clickOnMenuButton();

        leftNavMenuPage.clickOnLeftNavMenuItem(1);

        Assert.assertEquals(
                driver.getCurrentUrl(), "https://saucelabs.com/",
                "User should be redirected to the sauce labs website.");
    }

    @Test(priority = 20, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfLogoutOptionIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.clickOnMenuButton();

        leftNavMenuPage.waitForMenuToBeVisible();

        Assert.assertTrue(leftNavMenuPage.doesLogoutButtonExist(),
                "Logout link should exists on menu.");

        leftNavMenuPage.clickOnLeftNavMenuItem(2);

        Assert.assertEquals(
                driver.getCurrentUrl(), "https://www.saucedemo.com/",
                "User should be redirected to the login page.");
    }

    @Test(priority = 21, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfResetAppStateIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.clickOnMenuButton();

        leftNavMenuPage.waitForMenuToBeVisible();

        Assert.assertTrue(leftNavMenuPage.doesResetAppButtonExist(),
                "Reset App Button link should exist on menu.");

        leftNavMenuPage.clickOnLeftNavMenuItem(3);

        Assert.assertFalse(topNavMenuPage.doesCartBadgeExist(),
                "Cart badge icon should not exist.");
    }

    @Test(priority = 22, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheEkisButtonIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.clickOnMenuButton();

        leftNavMenuPage.waitForMenuToBeVisible();

        Assert.assertTrue(
                leftNavMenuPage.doesEkisButtonExist(),
                "Ekis button should be visible.");
    }

    @Test(priority = 23, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheEkisButtonIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.clickOnCartButton();

        topNavMenuPage.clickOnMenuButton();

        leftNavMenuPage.waitForMenuToBeVisible();

        Assert.assertTrue(
                leftNavMenuPage.doesEkisButtonExist(),
                "Ekis button should be visible.");

        leftNavMenuPage.clickEkisButton();
    }

    @Test(priority = 24, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsAddedIsPresented() {
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

        topNavMenuPage.clickOnShoppingCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");

        Assert.assertTrue(cartPage.doesAddedCartItemExist(), "Added items should be exist in cart");
    }

    @Test(priority = 25, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsTitleIsPresented() {
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

        topNavMenuPage.clickOnShoppingCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");

        Assert.assertTrue(cartPage.doesAddedCartItemExist(), "Added items should be exist in cart");

        Assert.assertEquals(cartPage.getItemTitleText(),
                "Sauce Labs Backpack",
                "Item's title should be visible.");
    }
    @Test(priority = 26, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsDescriptionIsPresented() {
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

        topNavMenuPage.clickOnShoppingCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");

        Assert.assertTrue(cartPage.doesDescriptionCartItemExist(), "Item's description should be visible.");
    }
    @Test(priority = 27, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsPriceIsPresented() {
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

        topNavMenuPage.clickOnShoppingCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");

        Assert.assertTrue(cartPage.doesPriceCartItemExist(), "Item's price should be visible.");
    }
    @Test(priority = 28, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheQuantityOfItemIsPresented() {
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

        topNavMenuPage.clickOnShoppingCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");

        Assert.assertTrue(cartPage.doesQuantityCartItemExist(), "Item's quantity should be visible.");
    }

    @Test(priority = 29, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsTitleIsClickable() {
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

        topNavMenuPage.clickOnShoppingCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");

        Assert.assertTrue(cartPage.doesItemTitleExist(),
                "Item's title should be visible.");

        cartPage.clickOnItemTitle();

        Assert.assertFalse(
                driver.getCurrentUrl() == baseUrl + "/cart.html",
                "Should be redirected form cart page to single item page after click on item title.");
    }

    @Test(priority = 30, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsTitleIsWorking() {
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

        topNavMenuPage.clickOnShoppingCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");

        Assert.assertTrue(cartPage.doesItemTitleExist(),
                "Item's title should be visible.");

        cartPage.clickOnItemTitle();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("https://www.saucedemo.com/inventory-item.html"),
                "Should be redirected to single item page after click on item title.");
    }


    @Test(priority = 31, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheRemoveButtonIsPresented() {
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

        topNavMenuPage.clickOnShoppingCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "/cart.html",
                "Should be redirected to cart page after click on cart button.");

        Assert.assertTrue(
                cartPage.doesRemoveCartBtnExist(),
                "Cart item remove button should be visible");
    }
}
