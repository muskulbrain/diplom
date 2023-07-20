package selenideTests.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.models.AddressBodyModels;
import selenideTests.models.FavoriteBodyModels;

import static io.restassured.RestAssured.given;
import static selenideTests.pages.API_Step.returnRequest;
import static selenideTests.specs.AddressSpecs.addressRequestSpec;
import static selenideTests.specs.AddressSpecs.addressResponseSpec;
import static selenideTests.specs.FavoriteSpecs.favoriteRequestSpec;
import static selenideTests.specs.FavoriteSpecs.favoriteResponseSpec;

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
        given(addressRequestSpec)
                .spec(request)
                .body(addressBody)
                .when()
                .post(Configuration.baseUrl + "/api/v1/address")
                .then()
                .spec(addressResponseSpec);

    }

    @Test
    public void addProductInFavorite() {

        FavoriteBodyModels favoriteBody = new FavoriteBodyModels();
        favoriteBody.setProductCode(402860);

        RequestSpecification request = given(favoriteRequestSpec);
        Header Token = new Header("token", TestBase.token);
        openBase();
        loginUserByRest(Token.getValue());
        request.header(Token)
                .body(favoriteBody)
                .when()
                .post(Configuration.baseUrl + "/api/v1/productFavorite")
                .then()
                .spec(favoriteResponseSpec);
    }
}




