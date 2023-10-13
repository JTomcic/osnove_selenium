package tests;

import org.testng.annotations.Test;

public class ProductsTests extends BasicTest{
    @Test
    public void test1() {
        driver.navigate().to("https://google.com");
    }

}
