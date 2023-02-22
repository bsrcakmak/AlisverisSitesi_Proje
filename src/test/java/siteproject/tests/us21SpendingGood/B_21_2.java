package siteproject.tests.us21SpendingGood;

import org.testng.annotations.Test;
import siteproject.pages.SpendingGoodHomePage;
import siteproject.pages.SpendingGoodShoppingPage;
import siteproject.utilities.ConfigReader;
import siteproject.utilities.Driver;
import siteproject.utilities.ReusableMethods;

public class B_21_2 {
    SpendingGoodHomePage spendingGoodHomePage;
    SpendingGoodShoppingPage spendingGoodShoppingPage;

    @Test
    public void us21_tc02() {
        spendingGoodHomePage = new SpendingGoodHomePage();
        spendingGoodShoppingPage = new SpendingGoodShoppingPage();

        // Login islemi yapar
        ReusableMethods.loginSpending();

        // Search box'a istedigi bir urunu girer
        ReusableMethods.waitFor(10);
        spendingGoodHomePage.searchBox.sendKeys(ConfigReader.getProperty("spending_good_urun"));

        // Arama butonuna tiklar
        ReusableMethods.waitFor(10);
        spendingGoodHomePage.searchButton.click();

        // Istedigi urune ait arama sonuclarini goruntuler
        String resultText= spendingGoodShoppingPage.aramaSonuclari.getText();
        assert resultText.contains(ConfigReader.getProperty("spending_good_urun"));

        // ADD to card'a tikla
        ReusableMethods.waitFor(3);
        spendingGoodShoppingPage.addToCart.click();

        // urunun sepete eklendigine dair mesaji gorur
        assert spendingGoodShoppingPage.popUp.isDisplayed();

        Driver.closeDriver();
    }
}
