package siteproject.tests.us06SpendingGood;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import siteproject.pages.SpendingGoodHomePage;
import siteproject.pages.SpendingGoodOrderPage;
import siteproject.pages.SpendingGoodShoppingPage;
import siteproject.utilities.ConfigReader;
import siteproject.utilities.Driver;
import siteproject.utilities.ReusableMethods;

public class B_06_4 {
    SpendingGoodHomePage spendingGoodHomePage;
    SpendingGoodShoppingPage spendingGoodShoppingPage;
    SpendingGoodOrderPage spendingGoodOrderPage;

    @Test
    public void us06_tc04(){
        spendingGoodHomePage=new SpendingGoodHomePage();
        spendingGoodShoppingPage=new SpendingGoodShoppingPage();
        spendingGoodOrderPage = new SpendingGoodOrderPage();

        // Login islemi yapar
        ReusableMethods.loginSpending();

        // Search box'a istedigi bir urunu girer
        ReusableMethods.waitFor(3);
        spendingGoodHomePage.searchBox.sendKeys(ConfigReader.getProperty("spending_good_urun"));

        //Arama butonuna tikla
        ReusableMethods.waitFor(3);
        spendingGoodHomePage.searchButton.click();

        //Istenen urune ait arama yapildigini dogrula
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

        // Ilk urunde Ouantity bolumunde "+" butonuna bas
        ReusableMethods.waitFor(3);
        ReusableMethods.clickByJS(spendingGoodOrderPage.plus);
        // spendingGoodOrderPage.plus.click();

        // Update Cart butonuna tikla
        ReusableMethods.waitFor(3);
        spendingGoodOrderPage.updateCard.click();

        // Urun miktarinin guncellendigini dogrular
        assert spendingGoodOrderPage.updatePopUp.isDisplayed();

        Driver.closeDriver();
    }
}
