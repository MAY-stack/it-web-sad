package com.it.web.sad.itwebsad;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;


public class JsonSchemaValidator {

    @Test
    public void testGet(){
        baseURI = "https://reqres.in/api";

        given()
                .get("/comments")
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath("comment-schema.json"));
                statusCode(200)
                        .body(data[1]);

    }

}
