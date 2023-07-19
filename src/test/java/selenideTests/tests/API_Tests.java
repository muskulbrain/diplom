package selenideTests.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.models.AddressBodyModels;
import selenideTests.models.FavoriteBodyModels;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static selenideTests.helpers.CustomAllureListener.withCustomTemplates;
import static selenideTests.pages.API_Step.returnRequest;

public class API_Tests extends TestBase {

    @Test
    public void wrongAuth() {

        openBase();
        wrongLoginUserByRest();
    }

    @Test
    public void addAddress() {

        AddressBodyModels addressBody = new AddressBodyModels();
        addressBody.setIndex("123");
        addressBody.setCountryId(21);
        addressBody.setCityId(272);
        addressBody.setStreet("testa111");
        addressBody.setHouse("55");
        addressBody.setApartment("14");
        addressBody.setEntrance("22");
        addressBody.setFloor("8");
        addressBody.setIsActive(true);

        Header Token = new Header("token", TestBase.token);
        openBase();
        loginUserByRest(Token.getValue());
        RequestSpecification request = returnRequest(Token.getValue());
        given().spec(request).body(addressBody)
                .filter(withCustomTemplates())
                .queryParams("IsDebug", "1")
                .when()
                .post(Configuration.baseUrl + "/api/v1/address")
                .then()
                .log().body()
                .contentType(JSON)
                .statusCode(201);

    }

    @Test
    public void addProductInFavorite() {

        FavoriteBodyModels favoriteBody = new FavoriteBodyModels();
        favoriteBody.setProductCode(402860);

        RequestSpecification request = given();
        Header Token = new Header("token", TestBase.token);
        openBase();
        loginUserByRest(Token.getValue());
        request.header(Token);
        request.log().all()
                .filter(withCustomTemplates())
                .body(favoriteBody)
                .contentType(JSON)
                .queryParams("IsDebug", "1")
                .when()
                .post(Configuration.baseUrl + "/api/v1/productFavorite")
                .then()
                .log().body()
                .statusCode(201);
    }
}




