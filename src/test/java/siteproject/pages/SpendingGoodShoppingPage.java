package siteproject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import siteproject.utilities.Driver;

public class SpendingGoodShoppingPage {

    public SpendingGoodShoppingPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//*[@class='breadcrumb']")
    public WebElement aramaSonuclari;

    @FindBy(xpath = "//*[@name='add-to-cart']")
    public WebElement addToCart;

    @FindBy(xpath = "//*[@class='woocommerce-message alert alert-simple alert-btn alert-success']")
    public WebElement popUp;

    @FindBy(xpath = "//*[@class='cart-toggle']")
    public WebElement card;

    @FindBy(xpath = "//*[text()='View cart']")
    public WebElement viewCard;


}
