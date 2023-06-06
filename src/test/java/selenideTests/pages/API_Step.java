package selenideTests.pages;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import selenideTests.common.Constants;
import selenideTests.common.TestBase;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class API_Step extends TestBase {

    public static int cartId = 0;
    public static int cartPackageId = 0;


    public static void FillCartData() {
        Response response = given().spec(returnRequest(TestBase.token))
                .when().get("https://kz.siberianwellness.com/api/v1/cart")
                .then()
                .log().all()
                .contentType(JSON)
                .assertThat()
                .statusCode(200)
                .extract().response();
        API_Step.cartId = response.path("List.Id");
        API_Step.cartPackageId = response.path("List.CartPackages.Id[0]");
    }

    @Step("Добавление товара в корзину")
    public static API_Step addProductAPI() {
        FillCartData();
        String body = String.format("{\"CartId\":%d,\"CartPackageId\":%d,\"ProductId\":16492,\"Quantity\":1}", API_Step.cartId, API_Step.cartPackageId);
        RequestSpecification request = given();
        Header Token = new Header("token", TestBase.token);
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

    public static void authorize(String token) {
        String postData =
                "{\n" +
                        "\"Contract\": \"" + Constants.CONTRACT + "\",\n" +
                        "\"Password\": \"" + Constants.PASSWORD + "\"\n" +
                        "}";
        RequestSpecification request = returnRequest(token);
        given().spec(request).body(postData)
                .when().post(Configuration.baseUrl + "/api/v1/auth")
                .then()
                .log().ifError()
                .contentType(JSON)
                .assertThat()
                .statusCode(201);
    }

    public static String returnToken() {
        String token = "";
        token = given().spec(returnRequest(""))
                .when().get("https://kz.siberianwellness.com/api/v1/myValidToken")
                .then()
                .log().all()
                .contentType(JSON)
                .assertThat()
                .statusCode(201)
                .extract().body().jsonPath().getString("Model.Token");
        return token;
    }

    public static RequestSpecification returnRequest(String token) {
        RequestSpecification request = new RequestSpecBuilder()
                .addHeader("token", token)
                .build();
        return request;
    }

}
