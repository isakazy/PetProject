package api;

import antities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class ApiPractice {

    @Test
    public void TestRestAssured(){
       Response response =  RestAssured.get("https://reqres.in/api/users/2");
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200);


        String email = response.jsonPath().getString("data.email");
        Assert.assertTrue(email.endsWith("@reqres.in"));
        String firs_name = response.jsonPath().getString("data.first_name");
        Assert.assertEquals(firs_name, "Janet");

    }


    @Test
    public void CashWiseAuthorisation(){
        RequestBody requestBody = new RequestBody();
        requestBody.setEmail("isakazy@gmail.com");
        requestBody.setPassword("isakazyamanbaev");
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("https://backend.cashwise.us/api/myaccount/auth/login");
        System.out.println(response.statusCode());
        response.prettyPrint();
    }




    @Test
    public void FirstTest(){

      Response response =  RestAssured.get("https://reqres.in/api/users/2");
        String email = response.jsonPath().getString("data.email");
        System.out.println(email);
        Assert.assertTrue(email.endsWith("@reqres.in"));
        int status = response.statusCode();
        Assert.assertEquals(status, 200);
    }


@Test
public void testName(){
       Response response = RestAssured.get("https://reqres.in/api/users/2");
       int status = response.statusCode();
       Assert.assertEquals(status, 200);
       String name = response.jsonPath().getString("data.first_name");
       Assert.assertEquals(name, "Janet");
}




@Test
    public void text(){
       Response response =  RestAssured.get("https://reqres.in/api/users/2");
       String text = response.jsonPath().getString("support.text");
    System.out.println(text);

}




@Test
    public void Post(){
        RequestBody requestBody = new RequestBody();
        requestBody.setEmail("isakazy@gmail.com");
        requestBody.setPassword("isakazyamanbaev");

        Response response =  RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("https://backend.cashwise.us/api/myaccount/auth/login");
    System.out.println(response.statusCode());
    String token = response.jsonPath().getString("jwt_token");
    System.out.println(token);

}








@Test
    public void GetAllSellers(){
        String token = CashWiseAuthorization.getToken();
        Response response = RestAssured.given().auth().oauth2(token).get(Config.getValue("cashwiseApiUrl")+ "/api/myaccount/bankaccount");
    System.out.println(response.statusCode());
    response.prettyPrint();

    String bankAccountName = response.jsonPath().getString("[0].bank_account_name");
    System.out.println(bankAccountName);
    String BofName = response.jsonPath().getString("[2].bank_account_name");
    System.out.println(BofName);
    String typeOfPay = response.jsonPath().getString("[3].type_of_pay");
    System.out.println(typeOfPay);
}




@Test
public void LoopBankAccountName(){
        String token = CashWiseAuthorization.getToken();
        Response response = RestAssured.given().auth().oauth2(token).get(Config.getValue("cashwiseApiUrl")+ "/api/myaccount/bankaccount");

        int size = response.jsonPath().getList("JSON").size();
    System.out.println(size);

    for(int i = 0; i < size; i ++ ){
       String bankName = response.jsonPath().getString("[" + i + "].bank_account_name");
       String payType = response.jsonPath().getString("["+ i + "].type_of_pay");
        Assert.assertFalse(bankName.trim().isEmpty());
        Assert.assertFalse(payType.trim().isEmpty());

    }
}


    @Test
    public void verifyProductList(){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl")+ "/api/myaccount/products";

        Map<String, Object> params = new HashMap<>();
        params.put("page", 1 );
        params.put("size", 10);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println(response.statusCode());
    }




    @Test public void getSingleSeller(){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl")+ "/api/myaccount/sellers/1718";

        Response response = RestAssured.given().auth().oauth2(token).get(url);
        int status = response.statusCode();
        Assert.assertEquals(200, status);
        String phoneNumber = response.jsonPath().getString("phone_number");
        Assert.assertFalse(phoneNumber.isEmpty());

    }



    @Test public void getAllSellers(){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl") + "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 60);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);

        int status = response.statusCode();
        Assert.assertEquals(200, status);

        int size = response.jsonPath().getList("responses").size();
        System.out.println("seller count: "+size);
        System.out.println();


        for(int i = 0; i < size; i ++ ){
            String companyName = response.jsonPath().getString("responses["+ i +"].seller_id");
            System.out.println("seller id: "+companyName);
         //   Assert.assertTrue(companyName.endsWith("@gmail.com"));
        }
    }










}

/*

 */