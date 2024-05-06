package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.GooglePages;
import utilities.Driver;

public class GoogleSearchSteps {

    GooglePages googlePages = new GooglePages();

    @Given("browser is open")
    public void browser_is_open() {
        Driver.getDriver();
        System.out.println("inside step User opened browser ");


    }

    @And("user is on google search page")
    public void user_is_on_google_search_page() {
        Driver.getDriver().get("https://google.com");
        System.out.println("inside step user is on google page");


    }

    @When("user provides search input")
    public void user_provides_search_input() {
        googlePages.googleSearchField.sendKeys("cucumber automation testing framework");
        System.out.println("inside step user is providing input to the search field");

    }

    @And("user hits search")
    public void user_hits_search() {
        googlePages.googleSearchField.sendKeys(Keys.ENTER);
        System.out.println("inside step user is hitting on search button ");

    }

    @Then("verify user navigated to search results")
    public void verify_user_navigated_to_search_results() {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals("cucumber automation testing framework - Google Search", actualTitle);
        System.out.println("inside step we are verifying the output of search ");
        Driver.getDriver().close();
        Driver.getDriver().quit();

    }

}
