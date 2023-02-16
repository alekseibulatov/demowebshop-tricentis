package com.bulatov.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.bulatov.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class LoginSpecs {
    public static RequestSpecification loginRequestSpec = with()
            .log().uri()
            .log().headers()
            .log().body()
            .filter(withCustomTemplates())
            .baseUri("https://demowebshop.tricentis.com");

    public static ResponseSpecification responseSpecification(int status) {
        return new ResponseSpecBuilder()
                .log(ALL)
                .expectStatusCode(status)
                .build();
    }
}
