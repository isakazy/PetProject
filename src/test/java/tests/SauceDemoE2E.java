package tests;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import pages.*;
import utilities.Config;
import utilities.Driver;

public class SauceDemoE2E {

    Faker faker = new Faker();
    SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();
    SauceDemoProductPage sauceDemoProductPage = new SauceDemoProductPage();
    SauceDemoCartPage sauceDemoCartPage = new SauceDemoCartPage();
    SauceDemoPersonalInfoPage sauceDemoPersonalInfoPage = new SauceDemoPersonalInfoPage();
    SauceDemoOverviewPage sauceDemoOverviewPage = new SauceDemoOverviewPage();
    SauceDemoCompletePage sauceDemoCompletePage = new SauceDemoCompletePage();





    @Test
    public void E2E(){
        // logging in to sauceDemo and verifying user actually logged in
        Driver.getDriver().get(Config.getValue("sauceDemo"));
        sauceDemoLoginPage.sauceDemoUsernameInputField.sendKeys(Config.getValue("sauceDemoUsername"));
        sauceDemoLoginPage.sauceDemoPasswordInputField.sendKeys(Config.getValue("sauceDemoPassword"));
        sauceDemoLoginPage.sauceDemoLoginButton.click();
        Assert.assertEquals(Config.getValue("sauceDemoProductPage"), Driver.getDriver().getCurrentUrl()); // verification point


        // product page
        sauceDemoProductPage.backPackButton.click();
        Assert.assertTrue(sauceDemoProductPage.oneInCart.isDisplayed()); // verify the one is displayed
        Assert.assertEquals("1", sauceDemoProductPage.oneInCart.getText()); // verify it only displays one
        sauceDemoProductPage.oneInCart.click();

        // cart page
        Assert.assertEquals(Config.getValue("sauceDemoCartPage"), Driver.getDriver().getCurrentUrl()); // verify redirection to the cart page
        Assert.assertTrue(sauceDemoCartPage.itemInTheCart.isDisplayed()); // verify item is in the cart page
        Assert.assertTrue(sauceDemoCartPage.removeFromCart.isDisplayed()); // verify remove button is displayed
        Assert.assertEquals("Remove", sauceDemoCartPage.removeFromCart.getText()); //verify remove button has text "remove"
        Assert.assertTrue(sauceDemoCartPage.checkOutButton.isDisplayed());// verify checkout button is displayed
        Assert.assertEquals("Checkout", sauceDemoCartPage.checkOutButton.getText()); // verify checkout button has text "Checkout"
        sauceDemoCartPage.checkOutButton.click();


        //User information Page
        Assert.assertEquals(Config.getValue("sauceDemoPersonalInfoPage"), Driver.getDriver().getCurrentUrl()); // verify redirection to the personal info page
        Assert.assertTrue(sauceDemoPersonalInfoPage.PersonalInfoField.isDisplayed()); // verify personal info input fields are displayed
        sauceDemoPersonalInfoPage.continueButton.click(); // clicking continue button without providing info to trigger error massage
        Assert.assertTrue(sauceDemoPersonalInfoPage.errorMassage.isDisplayed()); // verifying error massage pops up
        // providing personal info
        sauceDemoPersonalInfoPage.firstNameInputField.sendKeys(faker.name().firstName());
        sauceDemoPersonalInfoPage.lastNameInputField.sendKeys(faker.name().lastName());
        sauceDemoPersonalInfoPage.postalCodeInputField.sendKeys(faker.address().zipCode());
        Assert.assertTrue(sauceDemoPersonalInfoPage.continueButton.isDisplayed()); // verifying continue button is displayed
        sauceDemoPersonalInfoPage.continueButton.click();



        //overview page
        Assert.assertEquals(Config.getValue("sauceDemoOverviewPage"), Driver.getDriver().getCurrentUrl()); // verifying redirection to overview page
        Assert.assertTrue(sauceDemoOverviewPage.itemInOverviewPage.isDisplayed()); // verifying the item is displayed
        Assert.assertEquals("$29.99", sauceDemoOverviewPage.ItemPrice.getText()); // verifying the price of the item
        Assert.assertTrue(sauceDemoOverviewPage.finishButton.isDisplayed()); //  verifying finish button is displayed
        Assert.assertEquals("Finish", sauceDemoOverviewPage.finishButton.getText()); // verifying finish button has text finish
        sauceDemoOverviewPage.finishButton.click();


        // complete page
        Assert.assertEquals(Config.getValue("sauceDemoThankYouPage"), Driver.getDriver().getCurrentUrl()); // verify redirection to complete page
       Assert.assertTrue(sauceDemoCompletePage.thankYouText.isDisplayed()); // verify thank you text is displayed
       Assert.assertEquals("Thank you for your order!", sauceDemoCompletePage.thankYouText.getText()); // verify the text is correct
       Assert.assertTrue(sauceDemoCompletePage.BackHomeButton.isDisplayed()); // verify back home button is displayed
       Assert.assertEquals("Back Home", sauceDemoCompletePage.BackHomeButton.getText()); // verify back home button has correct text
       sauceDemoCompletePage.BackHomeButton.click();
       Assert.assertEquals(Config.getValue("sauceDemoProductPage"), Driver.getDriver().getCurrentUrl()); // verify redirection to product page

        Driver.getDriver().quit();






    }
}
