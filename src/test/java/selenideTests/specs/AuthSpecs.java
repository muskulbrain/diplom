package selenideTests.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static selenideTests.helpers.CustomAllureListener.withCustomTemplates;

public class AuthSpecs {

    public static RequestSpecification authRequestSpec = with()
            .log().all()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .queryParams("IsDebug", "1");

    public static ResponseSpecification authResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .build();

    public static RequestSpecification tokenRequestSpec = with()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .queryParams("IsDebug", "1");

    public static ResponseSpecification tokenResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification wrongAuthResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .build();
}
