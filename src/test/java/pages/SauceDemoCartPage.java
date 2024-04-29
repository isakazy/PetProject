package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoCartPage {

    public SauceDemoCartPage(){
        PageFactory.initElements(Driver.getDriver(), this );
    }

    @FindBy(xpath = "//div[@class='cart_item']")
    public WebElement itemInTheCart;


    @FindBy(xpath = "//button[.='Remove']")
    public WebElement removeFromCart;

    @FindBy(xpath = "//button[.='Checkout']")
    public WebElement checkOutButton;


}
