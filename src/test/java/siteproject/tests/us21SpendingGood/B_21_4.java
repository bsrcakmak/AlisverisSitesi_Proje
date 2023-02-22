package siteproject.tests.us21SpendingGood;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import siteproject.pages.SpendingGoodAdressPage;
import siteproject.pages.SpendingGoodHomePage;
import siteproject.pages.SpendingGoodOrderPage;
import siteproject.pages.SpendingGoodShoppingPage;
import siteproject.utilities.ConfigReader;
import siteproject.utilities.Driver;
import siteproject.utilities.ReusableMethods;

public class B_21_4 {
    SpendingGoodHomePage spendingGoodHomePage;
    SpendingGoodShoppingPage spendingGoodShoppingPage;
    SpendingGoodOrderPage spendingGoodOrderPage;
    SpendingGoodAdressPage spendingGoodAdressPage;

    @Test
    public void us21_tc04() {
        spendingGoodHomePage = new SpendingGoodHomePage();
        spendingGoodShoppingPage = new SpendingGoodShoppingPage();
        spendingGoodOrderPage = new SpendingGoodOrderPage();
        spendingGoodAdressPage = new SpendingGoodAdressPage();

        // Login islemi yapar
        ReusableMethods.loginSpending();

        // Search box'a istedigi bir urunu girer
        ReusableMethods.waitFor(10);
        spendingGoodHomePage.searchBox.sendKeys(ConfigReader.getProperty("spending_good_urun"));
        ReusableMethods.waitFor(10);

        // Arama butonuna tiklar
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
        //spendingGoodOrderPage.proceedToCheckout.click();

        // Sayfada Payment Method bolumunu goruntuler
        assert spendingGoodAdressPage.payment.isDisplayed();

        Driver.closeDriver();
    }
}
