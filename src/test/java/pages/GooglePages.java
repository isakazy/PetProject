package pages;

import io.cucumber.java.zh_cn.假如;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import javax.swing.*;
import java.awt.print.PageFormat;

public class GooglePages {
    public GooglePages(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//textarea[@id='APjFqb']")
    public WebElement googleSearchField;

        @FindBy(xpath = "//input[@data-ved='0ahUKEwjv26723_WFAxX8jIkEHaz2BgoQ4dUDCBY']")
    public WebElement searchButton;
}
