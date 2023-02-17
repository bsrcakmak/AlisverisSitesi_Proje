package siteproject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import siteproject.utilities.Driver;

public class SpendingGoodOrderPage {

    public SpendingGoodOrderPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//button[@class='quantity-plus w-icon-plus']")
    public WebElement plus;

    @FindBy(xpath = "//button[@name='update_cart']")
    public WebElement updateCard;

    @FindBy(xpath = "//*[@class='woocommerce-message alert alert-simple alert-icon alert-close-top alert-success']")
    public WebElement updatePopUp;

    @FindBy(xpath = "//*[@title='Minus']")
    public WebElement minus;

    @FindBy(xpath = "//*[@class='checkout-button button alt wc-forward']")
    public WebElement proceedToCheckout;
}
