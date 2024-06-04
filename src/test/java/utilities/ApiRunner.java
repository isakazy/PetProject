package utilities;

import antities.CustomResponse;
import antities.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;
import lombok.Getter;

import java.util.Map;


@Data
public class ApiRunner {

    @Getter
    private static  CustomResponse customResponse;

    public static void runGet(String path ){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl") + path;

        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("Status code: "+ response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            customResponse.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("probably a List of Objects");
        }
    }


    public static void runGet(String path, Map<String, Object> params) {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl") + path;

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println("Status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            customResponse.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("probably a List of Objects");
        }

    }

    public static void runPost(String path, RequestBody requestBody){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl") + path;

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);
        System.out.println("Status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            customResponse.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("probably a List of Objects");
        }

    }

    public static void runDELETE(String path ){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getValue("cashwiseApiUrl") + path;

        Response response = RestAssured.given().auth().oauth2(token).delete(url);
        System.out.println("Status code: "+ response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            customResponse.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("probably a List of Objects");
        }
    }

}
