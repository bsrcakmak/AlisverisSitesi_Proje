package siteproject.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import siteproject.utilities.Driver;

public class SpendingGoodHomePage {
    public SpendingGoodHomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//*[@class='login inline-type']")
    public WebElement singIn;
    @FindBy(id = "username")
    public WebElement spending_good_username;
    @FindBy(id = "password")
    public WebElement spending_good_password;
    @FindBy(xpath = "//button[@name='login']")
    public WebElement loginButton;
    @FindBy(xpath = "(//input[@class='form-control'])[1]")
    public WebElement searchBox;
    @FindBy(xpath = "//*[@aria-label='Search Button']")
    public WebElement searchButton;
}