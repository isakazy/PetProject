package stepsDefinition;
import antities.CustomResponse;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;
import io.cucumber.junit.CucumberOptions;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ApiRunner;

public class GetSellerApiSteps {
    Response response;


    @Given("get single seller api is hit {string}")
    public void get_single_seller_api_is_hit(String endPoint) {
        ApiRunner.runGet(endPoint + 4301) ;

    }
    @Then("verify the seller email is not empty")
    public void verify_the_seller_email_is_not_empty() {
        String email = ApiRunner.getCustomResponse().getEmail();
        Assert.assertFalse(email.isEmpty());
    }

    @Then("verify seller name is {string}")
    public void verify_seller_name_is(String isakazy) {

    }
}
