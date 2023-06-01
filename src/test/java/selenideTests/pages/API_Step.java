package selenideTests.pages;

import io.qameta.allure.Step;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import selenideTests.common.Constants;
import selenideTests.common.TestBase;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class API_Step extends TestBase {


    @Step("Добавление товара в корзину")
    public static API_Step addProductAPI() {
        String body = "{\"CartId\":1344793,\"CartPackageId\":1170893,\"ProductId\":16492,\"Quantity\":1}";

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
}
