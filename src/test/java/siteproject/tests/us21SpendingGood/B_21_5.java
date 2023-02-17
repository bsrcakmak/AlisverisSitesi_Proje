package siteproject.tests.us21SpendingGood;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import testNGproject.pages.*;
import testNGproject.utilities.ConfigReader;
import testNGproject.utilities.Driver;
import testNGproject.utilities.ReusableMethods;

public class B_21_5 {
    SpendingGoodHomePage spendingGoodHomePage;
    SpendingGoodShoppingPage spendingGoodShoppingPage;
    SpendingGoodOrderPage spendingGoodOrderPage;
    SpendingGoodAdressPage spendingGoodAdressPage;
    SpendingGoodOrderCompletePage spendingGoodOrderCompletePage;

    @Test
    public void us21_tc05() {
        spendingGoodHomePage = new SpendingGoodHomePage();
        spendingGoodShoppingPage = new SpendingGoodShoppingPage();
        spendingGoodOrderPage = new SpendingGoodOrderPage();
        spendingGoodAdressPage = new SpendingGoodAdressPage();
        spendingGoodOrderCompletePage = new SpendingGoodOrderCompletePage();

        // Login islemi yapar
        ReusableMethods.loginSpending();

        // Search box'a istedigi bir urunu girer
        ReusableMethods.waitFor(3);
        spendingGoodHomePage.searchBox.sendKeys(ConfigReader.getProperty("spending_good_urun"));

        // Arama butonuna tiklar
        ReusableMethods.waitFor(3);
        spendingGoodHomePage.searchButton.click();

        // Istedigi urune ait arama sonuclarini goruntuler
        String resultText= spendingGoodShoppingPage.aramaSonuclari.getText();
        assert resultText.contains(ConfigReader.getProperty("spending_good_urun"));

        // ADD to card'a tikla
        spendingGoodShoppingPage.addToCart.click();

        // urunun sepete eklendigine dair mesaji gorur
        assert spendingGoodShoppingPage.popUp.isDisplayed();

        // Kullanici Cart sekmesine tiklar
        ReusableMethods.waitFor(3);
        spendingGoodShoppingPage.card.click();

        // View Card'a tiklar
        ReusableMethods.waitFor(3);
        spendingGoodShoppingPage.viewCard.click();

        // Urunun eklendigini dogrular
        String actualUrun= Driver.getDriver().findElement(By.xpath("//a[text()='MATLAB 7.6']")).getText();
        String expectedUrun = ConfigReader.getProperty("spending_good_urun");
        assert actualUrun.contains(expectedUrun);

        // Proceed To Checkout butonuna tiklar
        ReusableMethods.clickByJS(spendingGoodOrderPage.proceedToCheckout);
        // spendingGoodOrderPage.proceedToCheckout.click();

        // Sayfada Payment Method bolumunu goruntuler
        assert spendingGoodAdressPage.payment.isDisplayed();

        // Enter Your Code butonuna tiklar
        ReusableMethods.waitFor(3);
        ReusableMethods.clickByJS(spendingGoodOrderCompletePage.enterCode);
        //spendingGoodOrderCompletePage.enterCode.click();

        // Apply Coupon bolumunun gorunur oldugunu test eder
        assert spendingGoodOrderCompletePage.couponTable.isDisplayed();

        // Kullanici kupon ismini girer
        spendingGoodOrderCompletePage.couponCode.sendKeys(ConfigReader.getProperty("spending_good_coupon"));

        // Apply Coupon butonuna tiklar
        ReusableMethods.clickByJS(spendingGoodOrderCompletePage.applyCoupon);
        // spendingGoodOrderCompletePage.applyCoupon.click();

        // Kodun basari bir sekilde eklendigini gorur
        assert spendingGoodOrderCompletePage.couponPopUp.isDisplayed();

        // Your Order bolumunde indirimli fiyati gorur
        assert spendingGoodOrderCompletePage.yourOrder.isDisplayed();

        // Odeme seceneklerinden ilkini secer
        ReusableMethods.waitFor(3);
        ReusableMethods.clickByJS(spendingGoodAdressPage.EFT);
        // spendingGoodAdressPage.EFT.click();

        // Place Order butonuna tiklar
        ReusableMethods.waitFor(3);
        spendingGoodAdressPage.placeOrder.click();

        // "Thank you. Your order has been received." mesajinin goruntulendigini dogrular
        String actualMessage = spendingGoodOrderCompletePage.message.getText();
        String expectedMessage = "Thank you. Your order has been received.";
        Assert.assertEquals(expectedMessage,actualMessage);

        Driver.closeDriver();
    }
}
