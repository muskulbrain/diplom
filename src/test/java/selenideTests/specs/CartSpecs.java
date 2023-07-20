package selenideTests.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static selenideTests.helpers.CustomAllureListener.withCustomTemplates;

public class CartSpecs {

    public static RequestSpecification fillCartRequestSpec = with()
            .log().all()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .queryParams("IsDebug", "1");

    public static ResponseSpecification fillCartResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();

    public static RequestSpecification addCartRequestSpec = with()
            .log().all()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .queryParams("IsDebug", "1");

    public static ResponseSpecification addCartResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .build();

}
