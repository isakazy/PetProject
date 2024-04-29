package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoOverviewPage {

    public SauceDemoOverviewPage(){
        PageFactory.initElements(Driver.getDriver(), this );
    }

    @FindBy(xpath = "//div[@class='cart_item']" )
    public WebElement itemInOverviewPage;


    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public WebElement ItemPrice;

    @FindBy(xpath = "//button[@id='finish']")
    public WebElement finishButton;

    @FindBy(xpath = "//h2[.='Thank you for your order!']")
    public WebElement thankYouText;

    @FindBy(xpath = "//button[@id='back-to-products']")
    public WebElement BackHomeButton;
}
