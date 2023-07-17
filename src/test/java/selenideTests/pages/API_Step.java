package selenideTests.pages;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import selenideTests.common.TestBase;
import selenideTests.models.AuthModels;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static selenideTests.common.Constants.*;

public class API_Step extends TestBase {

    public static int cartId = 0;
    public static int cartPackageId = 0;


    // Получить и записать переменные корзины
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

    //Подставить токен и авторизоваться
    public static void authorize(String token) {
        AuthModels authBody = new AuthModels();
        authBody.setContract(CONTRACT);
        authBody.setPassword(PASSWORD);

        RequestSpecification request = returnRequest(token);
        given().spec(request).body(authBody)
                .when().post(Configuration.baseUrl + "/api/v1/auth")
                .then()
                .log().ifError()
                .contentType(JSON)
                .assertThat()
                .statusCode(201);
    }

    //Подставить токен и авторизоваться с неправильным паролем
    public static void wrongAuthorize(String token) {
        AuthModels authBody = new AuthModels();
        authBody.setContract(CONTRACT);
        authBody.setPassword(WRONGPASSWORD);

        RequestSpecification request = returnRequest(token);
        given().spec(request).body(authBody)
                .queryParams("IsDebug", "1")
                .when().post(Configuration.baseUrl + "/api/v1/auth")
                .then()
                .log().ifError()
                .contentType(JSON)
                .assertThat()
                .statusCode(400);

    }

    //Вернуть токен
    public static String returnToken() {
        String token = "";
        token = given().spec(returnRequest(""))
                .when().get("https://kz.siberianwellness.com/api/v1/myValidToken")
                .then()
                .log().ifError()
                .contentType(JSON)
                .assertThat()
                .statusCode(201)
                .extract().body().jsonPath().getString("Model.Token");
        return token;
    }

    //Вернуть подготовленный объект
    public static RequestSpecification returnRequest(String token) {
        RequestSpecification request = new RequestSpecBuilder()
                .addHeader("token", token)
                .build();
        return request;
    }

    //Вернуть объект city по id города
    /*public void String returnCity(String CityId) {
        String string = "";
        String response = given().spec(returnRequest(token))
                .when().get("https://kz.siberianwellness.com/api/v1/city/272")
                .then()
                .log().all()
                .contentType(JSON)
                .assertThat()
                .statusCode(200)
                .extract().body().jsonPath().getObject("Model", Pojo.class).toString();
                //response().asString();
        //string = response.body();
        //String city = response.body.jsonPath().getObject("Model", Pojo.class).toString();
                //JsonPath.read(response, "$.Model").toString();
        //return city;

    }
*/
}
