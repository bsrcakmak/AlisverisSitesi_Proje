package siteproject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import siteproject.utilities.Driver;

public class SpendingGoodOrderCompletePage {

    public SpendingGoodOrderCompletePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//*[text()='Thank you. Your order has been received.']")
    public WebElement message;

    @FindBy(xpath = "//*[text()='Enter your code']")
    public WebElement enterCode;

    @FindBy(xpath = "//*[@class='checkout_coupon woocommerce-form-coupon']")
    public WebElement couponTable;

    @FindBy(id = "coupon_code")
    public WebElement couponCode;

    @FindBy(xpath = "//*[text()='Apply coupon']")
    public WebElement applyCoupon;

    @FindBy(xpath = "//*[@class='woocommerce-message alert alert-simple alert-icon alert-close-top alert-success']")
    public WebElement couponPopUp;

    @FindBy(xpath = "//*[@class='cart-discount coupon-catwoman']")
    public WebElement yourOrder;

}
