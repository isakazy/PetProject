package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoPersonalInfoPage {
    public SauceDemoPersonalInfoPage(){
        PageFactory.initElements(Driver.getDriver(), this );
    }

    @FindBy(xpath = "//div[@class='checkout_info']")
    public WebElement PersonalInfoField;

    @FindBy(xpath = "//input[@id='first-name']")
    public WebElement firstNameInputField;

    @FindBy(xpath = "//input[@id='last-name']")
    public WebElement lastNameInputField;


    @FindBy(xpath = "//input[@id='postal-code']")
    public WebElement postalCodeInputField;

    @FindBy(xpath = "//input[@id='continue']")
    public WebElement continueButton;

    @FindBy(xpath = "//div[@class='error-message-container error']")
    public WebElement errorMassage;



}
