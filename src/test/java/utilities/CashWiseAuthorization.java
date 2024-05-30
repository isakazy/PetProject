package utilities;

import api.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CashWiseAuthorization {
    public static String getToken(){
        RequestBody requestBody = new RequestBody();
        requestBody.setEmail("isakazy@gmail.com");
        requestBody.setPassword("isakazyamanbaev");

        String authUrl = "https://backend.cashwise.us/api/myaccount/auth/login";
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post(authUrl);
        return response.jsonPath().getString("jwt_token");
    }



}
