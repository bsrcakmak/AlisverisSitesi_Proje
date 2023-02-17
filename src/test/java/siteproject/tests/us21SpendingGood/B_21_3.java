package siteproject.tests.us21SpendingGood;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import testNGproject.pages.SpendingGoodHomePage;
import testNGproject.pages.SpendingGoodShoppingPage;
import testNGproject.utilities.ConfigReader;
import testNGproject.utilities.Driver;
import testNGproject.utilities.ReusableMethods;

public class B_21_3 {
    SpendingGoodHomePage spendingGoodHomePage;
    SpendingGoodShoppingPage spendingGoodShoppingPage;

    @Test
    public void us21_tc03() {
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

        Driver.closeDriver();
    }
}
