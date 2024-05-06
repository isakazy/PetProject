package stepsDefinition;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.SauceDemoLoginPage;
import utilities.Config;
import utilities.Driver;

public class loginDemoSteps {

    SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();


    @Given("user is on sauce demo login page")
    public void user_is_on_sauce_demo_login_page() {
      Driver.getDriver().get(Config.getValue("sauceDemo"));

    }

    @When("user provides a valid username")
    public void user_provides_a_valid_username() {
       sauceDemoLoginPage.sauceDemoUsernameInputField.sendKeys(Config.getValue("sauceDemoUsername"));

    }

    @When("user provides valid password")
    public void user_provides_valid_password() {
        sauceDemoLoginPage.sauceDemoPasswordInputField.sendKeys(Config.getValue("sauceDemoPassword"));


    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
      sauceDemoLoginPage.sauceDemoLoginButton.click();

    }

    @Then("verify user successfully logged in")
    public void verify_user_successfully_logged_in() {
        Assert.assertEquals(Config.getValue("sauceDemoProductPage"), Driver.getDriver().getCurrentUrl());
        Driver.getDriver().close();
        Driver.getDriver().quit();

    }

    @When("user provides a invalid username")
    public void user_provides_a_invalid_username() {


    }
    @When("user provides invalid password")
    public void user_provides_invalid_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("verify user sees an error message")
    public void verify_user_sees_an_error_message() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
