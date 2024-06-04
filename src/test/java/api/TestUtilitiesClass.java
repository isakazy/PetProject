package api;

import antities.RequestBody;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.manipulation.Ordering;
import utilities.ApiRunner;

import java.util.HashMap;
import java.util.Map;

public class TestUtilitiesClass {

    @Test
    public void TestRunSingleSeller(){
        ApiRunner.runGet("/api/myaccount/sellers/" + 1718);
        String sellerName = ApiRunner.getCustomResponse().getSeller_name();
        String companyName = ApiRunner.getCustomResponse().getCompany_name();
        String email = ApiRunner.getCustomResponse().getEmail();

        Assert.assertFalse(sellerName.isEmpty());
        Assert.assertFalse(companyName.isEmpty());
        Assert.assertFalse(email.isEmpty());

    }


    @Test
    public void CreateSellerGetSellerDeleteSeller(){

        // create a seller
        String postUrl ="/api/myaccount/sellers";
        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();

        requestBody.setCompany_name("Wintrust Financial Corporation");
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
        requestBody.setAddress(faker.address().fullAddress());

        ApiRunner.runPost(postUrl, requestBody);

        int sellerID = ApiRunner.getCustomResponse().getSeller_id();;

        String getUrl = "/api/myaccount/sellers/" + sellerID;

        ApiRunner.runGet(getUrl);

        String companyName = ApiRunner.getCustomResponse().getCompany_name();
        Assert.assertEquals("Wintrust Financial Corporation", companyName);

        // delete a seller
        ApiRunner.runDELETE("/api/myaccount/sellers/" + sellerID);

        // get all sellers
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1 );
        params.put("size", 30);

        ApiRunner.runGet("/api/myaccount/sellers", params);

        int size = ApiRunner.getCustomResponse().getResponses().size();

       boolean isPresent = true;

       // verify the seller is deleted
       for(int i = 0; i < size; i ++ ){
           String allCompanyName = ApiRunner.getCustomResponse().getResponses().get(i).getCompany_name();
           if(!allCompanyName.equals("Wintrust Financial Corporation")){
               isPresent = false;
           }
       }
       Assert.assertFalse(isPresent);

    }
}
