package stepsDefinition;

import antities.RequestBody;
import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.junit.Assert;
import utilities.ApiRunner;

import java.util.HashMap;
import java.util.Map;

public class CreateDeleteSellerAPiSteps {
    int id;

    @Given("user hits create seller api with {string}")
    public void user_hits_create_seller_api_with(String PostEndPoint) {
        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();

        requestBody.setCompany_name("testCucumber");
        requestBody.setSeller_name("cucumberAPi");
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number("3127795529");
        requestBody.setAddress("2239 North Western ave");

        ApiRunner.runPost(PostEndPoint, requestBody);

    }

    @Then("verify user id is generated")
    public void verify_user_id_is_generated() {
        Assert.assertNotEquals(0, ApiRunner.getCustomResponse().getSeller_id());
        id = ApiRunner.getCustomResponse().getSeller_id();
    }

    @Then("get single seller api is hit with {string}")
    public void get_single_seller_api_is_hit_with(String getSingleSellerAPi) {
        ApiRunner.runGet(getSingleSellerAPi + id);

    }

    @Then("verify seller name is not empty")
    public void verify_seller_name_is_not_empty() {
        String sellerName = ApiRunner.getCustomResponse().getSeller_name();
        Assert.assertFalse(sellerName.isEmpty());
    }


    @Then("verify seller email is not empty")
    public void verify_seller_email_is_not_empty() {
        String email = ApiRunner.getCustomResponse().getEmail();
        Assert.assertFalse(email.isEmpty());
    }


    @Then("delete seller api is hit with {string}")
    public void delete_seller_api_is_hit_with(String deleteEndPoint) {
       ApiRunner.runDELETE(deleteEndPoint + id);
    }


    @Then("get all sellers api is hit with {string}")
    public void get_all_sellers_api_is_hit_with(String allSellerAPIEndPoint) {
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1 );
        params.put("size", 50);
        ApiRunner.runGet(allSellerAPIEndPoint, params);
    }


    @Then("verify deleted seller is not present in the list")
    public void verify_deleted_seller_is_not_present_in_the_list() {
       int size = ApiRunner.getCustomResponse().getResponses().size();

       boolean isPresent = true;
       for(int i =0; i < size; i ++ ){
           int sellerId = ApiRunner.getCustomResponse().getResponses().get(i).getSeller_id();
           if (sellerId != id) {
               isPresent = false;
               break;
           }

       }
       Assert.assertFalse(isPresent);

    }

}
