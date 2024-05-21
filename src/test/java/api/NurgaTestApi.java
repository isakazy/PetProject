package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.N;
import org.junit.Assert;
import org.junit.Test;

public class NurgaTestApi {

    @Test
    public void testApi(){
      Response response =  RestAssured.get("https://reqres.in/api/users/2");
      int status = response.statusCode();
      Assert.assertEquals(200, status );
    }

    @Test
    public void TestResponse(){
       Response response =  RestAssured.get("https://reqres.in/api/users/2");
       String email = response.jsonPath().getString("data.email");
        Assert.assertTrue(email.endsWith("@reqres.in"));
    }

    @Test
    public void TestFirstName(){
       Response response = RestAssured.get("https://reqres.in/api/users/2");
       int status = response.statusCode();
       Assert.assertEquals(200, status);
       String name = response.jsonPath().getString("data.first_name");
       Assert.assertEquals("Janet", name);
       response.prettyPrint();
    }


    @Test
    public void PostAPi(){

        NurgaRequestBody nurgaRequestBody = new NurgaRequestBody();
        nurgaRequestBody.setEmail("isakazy@gmail.com");
        nurgaRequestBody.setPassword("isakazyamanbaev");
        Response response =  RestAssured.given().contentType(ContentType.JSON).body(nurgaRequestBody).post("https://backend.cashwise.us/api/myaccount/auth/login");
        response.prettyPrint();

    }
}
