package stepsDefinition;
import antities.RequestBody;
import com.github.javafaker.Faker;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;
import org.junit.Assert;
import utilities.ApiRunner;

import java.util.HashMap;
import java.util.Map;

public class CreateDeleteSellerAPiSteps {
    int id;
    int size;
    int idToArchive;



    @Given("User hits get single seller api {string}")
    public void user_hits_get_single_seller_api(String getUrl) {
        ApiRunner.runGet(getUrl + 4303);
    }

    @Then("verify email is not empty")
    public void verify_email_is_not_empty() {
        String email =  ApiRunner.getCustomResponse().getEmail();
        Assert.assertFalse(email.isEmpty());
    }

    @Then("hit put api with {string} and change an email to {string}")
    public void hit_put_api_with_and_change_an_email_to(String putUrl, String email) {
        Faker faker = new Faker();
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(email);
        requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
        requestBody.setAddress(faker.address().fullAddress());
        ApiRunner.runPut(putUrl + 4303, requestBody);

    }

    @Then("verify user email is as expected")
    public void verify_user_email_is_as_expected() {
        String actualEmail =  ApiRunner.getCustomResponse().getEmail();
        Assert.assertEquals(actualEmail, "whateverEmail@gmail.com");

    }













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








    @Given("user hits get all sellrs api with {string}")
    public void user_hits_get_all_sellers_api_with(String endPoint) {
       Map<String, Object> params = new HashMap<>();
       params.put("isArchived", false);
       params.put("page", 1);
       params.put("size", 60);
       ApiRunner.runGet(endPoint, params);
       size = ApiRunner.getCustomResponse().getResponses().size();
    }

    @Then("Verify email is not emty")
    public void verify_email_is_not_epty() {
        for(int i = 0; i < size; i ++ ){
            String email = ApiRunner.getCustomResponse().getResponses().get(i).getEmail();
            Assert.assertFalse(email.isEmpty());
        }

    }










    @Given("user hit get a single seller api with {string}")
    public void user_hit_get_a_single_seller_api_with(String getSingleSellerUrl ) {
        ApiRunner.runGet(getSingleSellerUrl + 4308);
        idToArchive = ApiRunner.getCustomResponse().getCategory_id();

    }


    @Given("user hits archive the seller with {string}")
    public void user_hits_archive_the_seller_with(String archiveUrl) {
        Map<String, Object> params = new HashMap<>();
        params.put("sellersIdsForArchive", idToArchive);
        params.put("archive", true);

        ApiRunner.runPost(archiveUrl, params);


    }


    @Then("hit get all sellers and verify the seller was archived {string}")
    public void hit_get_all_sellers_and_verify_the_seller_was_archived(String getAllSellersUrl) {
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", true);
        params.put("page", 1 );
        params.put("size", 100);

        ApiRunner.runGet(getAllSellersUrl, params);

        int size = ApiRunner.getCustomResponse().getResponses().size();

        boolean Archived = false;

        for(int i = 0; i < size; i ++ ){
          int id = ApiRunner.getCustomResponse().getSeller_id();
            if (id == idToArchive) {
                Archived = true;
                break;
            }
        }
        Assert.assertTrue(Archived);


    }




}
