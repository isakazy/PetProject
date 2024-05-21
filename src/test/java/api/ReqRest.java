package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.devtools.v121.fetch.model.AuthChallengeResponse;

public class ReqRest {

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
        requestBody requestBody = new requestBody();
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
        requestBody requestBody = new requestBody();
        requestBody.setEmail("isakazy@gmail.com");
        requestBody.setPassword("isakazyamanbaev");

        
        Response response =  RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("https://backend.cashwise.us/api/myaccount/auth/login");
    System.out.println(response.statusCode());
    String token = response.jsonPath().getString("jwt_token");
    System.out.println(token);

}










}

/*

 */