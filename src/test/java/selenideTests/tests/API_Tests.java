package selenideTests.tests;

import com.codeborne.selenide.Configuration;
import com.jayway.jsonpath.JsonPath;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.pages.API_Step;

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
        String body = String.format("{ \"Index\": \"6666\", \"CountryId\": 21, \"CityId\": 272,\"City\": %s,\"Street\": \"Коммуна\",\"House\": \"9\", \"Apartment\": \"30\", \"Entrance\": \"\", \"Floor\": \"\", \"isActive\": true }", API_Step.returnCity(DEFAULTCITYID));
        RequestSpecification request = returnRequest(TestBase.token);
        given().spec(request).body(body)
                .when().post(Configuration.baseUrl + "/api/v1/address?RegionId=22&LanguageId=9&CityId=272&UserTimeZone=7&IsDebug=1")
                .then()
                .log().all()
                .contentType(JSON)
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void returnCity() {
        String string = "";
        String response = given().spec(returnRequest(token))
                .when().get(Configuration.baseUrl + "/api/v1/city/" + 272)
                .then()
                .log().all()
                .contentType(JSON)
                .assertThat()
                .statusCode(200)
                .extract().response().asString();
        //string = response.body();
        String city = JsonPath.read(response, "$.Model").toString();
        assert 2 == 2;
        //return city;

    }

}




