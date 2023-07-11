package selenideTests.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static selenideTests.common.Constants.*;
import static selenideTests.pages.API_Step.returnRequest;

public class API_Tests extends TestBase {

    @Test
    public void wrongAuth() {
        {
            String token = "";
            RequestSpecification request = new RequestSpecBuilder()
                    .addHeader(TOKEN_NAME, token)
                    .build();
            token = given().spec(request)
                    .when().get(Configuration.baseUrl + "/api/v1/myValidToken")
                    .then()
                    .log().ifError()
                    .contentType(JSON)
                    .assertThat()
                    .statusCode(201)
                    .extract().body().jsonPath().getString("Model.Token");
            String postData =
                    "{\n" +
                            "\"Contract\": \"" + CONTRACT + "\",\n" +
                            "\"Password\": \"" + WRONGPASSWORD + "\"\n" +
                            "}";
            request = new RequestSpecBuilder()
                    .addHeader(TOKEN_NAME, token)
                    .build();
            given().spec(request).body(postData)
                    .when().post(Configuration.baseUrl + "/api/v1/auth")
                    .then()
                    .log().ifError()
                    .contentType(JSON)
                    .assertThat()
                    .statusCode(400);
        }
    }

    @Test
    public void addAddress() {
        String body = String.format("{ \"Index\": \"123\", \"CountryId\": 21, \"CityId\": 272, \"Street\": \"testovaya\", \"House\": \"1\", \"Apartment\": \"2\", \"Entrance\": \"3\", \"Floor\": \"3\", \"isActive\": true }");
        RequestSpecification request = returnRequest(TestBase.token);
        given().spec(request).body(body)
                .queryParams("IsDebug", "1")
                .when()
                .log().ifValidationFails()
                .post("https://kz.siberianwellness.com/api/v1/address?RegionId=22&LanguageId=9&CityId=272&UserTimeZone=7")
                .then()
                .contentType(JSON)
                //.statusCode(201)
                .log().body();
        //.assertThat();

    }

    @Test
    public void addProductInFavorite() {
        String body = String.format("{\"ProductCode\":402860}");
        RequestSpecification request = given();
        Header Token = new Header("token", TestBase.token);
        openBase();
        loginUserByRest(Token.getValue());
        request.header(Token);
        request.log().all()
                .body(body)
                .contentType(JSON)
                .when()
                .post(Configuration.baseUrl + "/api/v1/productFavorite")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);
    }
}




