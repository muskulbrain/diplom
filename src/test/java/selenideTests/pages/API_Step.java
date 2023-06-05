package selenideTests.pages;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import selenideTests.common.Constants;
import selenideTests.common.TestBase;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class API_Step extends TestBase {


    @Step("Добавление товара в корзину")
    public static API_Step addProductAPI() {
        String body = "{\"CartId\":1362419,\"CartPackageId\":1188994,\"ProductId\":16492,\"Quantity\":1}";

        RequestSpecification request = given();
        Header Token = new Header("token", Constants.TOKEN);
        request.header(Token);
        request.log().all()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://kz.siberianwellness.com/api/v1/cartPackageProduct?RegionId=22&LanguageId=9&CityId=272&UserTimeZone=7&IsDebug=1")

                .then()
                .log().status()
                .log().body()
                .statusCode(201);
        return null;
    }

    public static String authorize() {
        String token = "";
        RequestSpecification request = new RequestSpecBuilder()
                .addHeader("token", token)
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
                        "\"Contract\": \"" + Constants.CONTRACT + "\",\n" +
                        "\"Password\": \"" + Constants.PASSWORD + "\"\n" +
                        "}";
        request = new RequestSpecBuilder()
                .addHeader("token", token)
                .build();
        given().spec(request).body(postData)
                .when().post(Configuration.baseUrl + "/api/v1/auth")
                .then()
                .log().ifError()
                .contentType(JSON)
                .assertThat()
                .statusCode(201);
        return token;
    }
}
