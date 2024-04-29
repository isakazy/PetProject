package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoLoginPage {
    public SauceDemoLoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(xpath = "//input[@id='user-name']")
    public WebElement sauceDemoUsernameInputField;


    @FindBy(xpath = "//input[@id='password']")
    public WebElement sauceDemoPasswordInputField;

    @FindBy(xpath = "//input[@id='login-button']")
    public WebElement sauceDemoLoginButton;
}
