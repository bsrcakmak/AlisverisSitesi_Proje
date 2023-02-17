package siteproject.tests.us06SpendingGood;

import org.testng.annotations.Test;
import testNGproject.pages.SpendingGoodHomePage;
import testNGproject.pages.SpendingGoodShoppingPage;
import testNGproject.utilities.ConfigReader;
import testNGproject.utilities.Driver;
import testNGproject.utilities.ReusableMethods;

public class B_06_1 {
    SpendingGoodHomePage spendingGoodHomePage;
    SpendingGoodShoppingPage spendingGoodShoppingPage;

    @Test
    public void us06_tc01(){
        spendingGoodHomePage=new SpendingGoodHomePage();
        spendingGoodShoppingPage=new SpendingGoodShoppingPage();

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

        Driver.closeDriver();
    }
}