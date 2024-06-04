package api;

import antities.CustomResponse;
import antities.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ApiRunner;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class POJoPractice {


    //create a category
    @Test
    public void CreateCategory() {
        String url = Config.getValue("cashwiseApiUrl") + "/api/myaccount/categories";
        String token = CashWiseAuthorization.getToken();

        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("CodeWiseAutomation");
        requestBody.setCategory_description("testPOJO");
        requestBody.setFlag(false);


        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
        int status = response.statusCode();
        Assert.assertEquals(201, status);
        response.prettyPrint();
    }


    /// create another category with custom response and POJO
    @Test
    public void testCustom() throws JsonProcessingException {
        String url = Config.getValue("cashwiseApiUrl") + "/api/myaccount/categories";
        String token = CashWiseAuthorization.getToken();

        RequestBody requestBody = new RequestBody();

        requestBody.setCategory_title("transportation");
        requestBody.setCategory_description("uber");
        requestBody.setFlag(false);

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);

        int status = response.statusCode();
        Assert.assertEquals(201, status);
        response.prettyPrint();

        ObjectMapper objectMapper = new ObjectMapper();

        CustomResponse customResponse = objectMapper.readValue(response.asString(), CustomResponse.class);

        System.out.println(customResponse.getCategory_id());


    }

    // create a cashWise seller

    @Test
    public void CreateSeller() throws JsonProcessingException {
        Faker faker = new Faker();
        String url = Config.getValue("cashwiseApiUrl") + "/api/myaccount/sellers";
        String token = CashWiseAuthorization.getToken();

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("trucking Company");
        requestBody.setSeller_name("seller is me");
        requestBody.setPhone_number("3127795529");
        requestBody.setEmail(faker.internet().emailAddress());

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);

        int status = response.statusCode();
        Assert.assertEquals(201, status);

        ObjectMapper objectMapper = new ObjectMapper();
        CustomResponse customResponse = objectMapper.readValue(response.asString(), CustomResponse.class);

        String expectedSellerName = "seller is me";

        Assert.assertEquals(expectedSellerName, customResponse.getSeller_name());

    }


    // create seller - > store the seller id -> hit get single seller api and verify status is 200
    @Test
    public void CreateGetSeller() throws JsonProcessingException {
        Faker faker = new Faker();
        String url = Config.getValue("cashwiseApiUrl") + "/api/myaccount/sellers";
        String token = CashWiseAuthorization.getToken();

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("car rentals");
        requestBody.setSeller_name("i lend lexus");
        requestBody.setPhone_number("3127795529");
        requestBody.setEmail(faker.internet().emailAddress());

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);

        ObjectMapper objectMapper = new ObjectMapper();
        CustomResponse customResponse = objectMapper.readValue(response.asString(), CustomResponse.class);


        int sellerID = customResponse.getSeller_id();
        System.out.println(sellerID);

        String getSellerUrl = Config.getValue("cashwiseApiUrl") + "/api/myaccount/sellers/" + sellerID;

        Response response1 = RestAssured.given().auth().oauth2(token).get(getSellerUrl);

        int statusCode = response1.statusCode();
        Assert.assertEquals(200, statusCode);


    }


    // create many sellers

    @Test
    public void CreateManySellers() {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl") + "/api/myaccount/sellers/";
        Faker faker = new Faker();

        for (int i = 0; i < 15; i++) {
            RequestBody requestBody = new RequestBody();

            requestBody.setCompany_name(faker.company().name());
            requestBody.setSeller_name(faker.name().firstName());
            requestBody.setEmail(faker.internet().emailAddress());
            requestBody.setAddress(faker.address().fullAddress());
            requestBody.setPhone_number(faker.phoneNumber().phoneNumber());

            Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                    .body(requestBody).post(url);

            int status = response.statusCode();
            Assert.assertEquals(201, status);

        }
    }

    // loop through the list of objects using POJO and verify emails are not empty
    @Test
    public void GetAllSellers() throws JsonProcessingException {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl") + "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 40);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        int size = customResponse.getResponses().size();

        for (int i = 0; i < size; i++) {
            String email = customResponse.getResponses().get(i).getEmail();
            Assert.assertFalse(email.isEmpty());
        }
    }

    @Test
    public void TestArchive() throws JsonProcessingException {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl") + "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 50);

        Response response = RestAssured.given().auth().oauth2(token)
                .params(params).get(url);

        int status = response.statusCode();
        Assert.assertEquals(200, status);

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);


        int size = customResponse.getResponses().size();
        System.out.println(size);

        String urlToArchive = Config.getValue("cashwiseApiUrl") + "/api/myaccount/sellers/archive/unarchive";

        for (int i = 0; i < size; i++) {
            if (customResponse.getResponses().get(i).getEmail().endsWith("@hotmail.com")) {
                int id = customResponse.getResponses().get(i).getSeller_id();

                Map<String, Object> paramsToArchive = new HashMap<>();
                paramsToArchive.put("sellersIdForArchive", id);
                paramsToArchive.put("Archive", true);


                RestAssured.given().auth().oauth2(token).params(paramsToArchive).contentType(ContentType.JSON).post(urlToArchive);
            }
        }
    }

    //bug - archive same seller twice, then get a non-existing seller - > attempt to archive a non-existing seller
    //bug - create a seller without email, and create a seller without phone number, create seller without anything and
    //verify in the ui and status code


    // archive a user
    @Test
    public void archiveUser() {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl") + "/api/myaccount/sellers/archive/unarchive";

        Map<String, Object> params = new HashMap<>();

        params.put("sellersIdsForArchive", 4212);
        params.put("archive", true);

        Response response = RestAssured.given().auth().oauth2(token)
                .params(params).post(url);

        int status = response.statusCode();
        Assert.assertEquals(200, status);
        response.prettyPrint();
    }



    //create a seller

    @Test
    public void CreateSellerTest() throws JsonProcessingException {
        Faker faker = new Faker();
        String url = Config.getValue("cashwiseApiUrl") + "/api/myaccount/sellers";
        String token = CashWiseAuthorization.getToken();

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("test double create");
        requestBody.setSeller_name("we will create many sellers ");
        requestBody.setPhone_number("3127795529");
        requestBody.setAddress("9700 West Higgins road");
        requestBody.setEmail(faker.internet().emailAddress());

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);

        Assert.assertEquals(201, response.statusCode());
    }

}
