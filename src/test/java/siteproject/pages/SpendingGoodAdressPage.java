package siteproject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import siteproject.utilities.Driver;

public class SpendingGoodAdressPage {

    public SpendingGoodAdressPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id="customer_details")
    public WebElement billingDetails;

    @FindBy(id = "payment")
    public WebElement payment;

    @FindBy(id = "payment_method_bacs")
    public WebElement EFT;

    @FindBy(id = "place_order")
    public WebElement placeOrder;
}
