import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

public class RestAssuredSpecGet {

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/")
            .setContentType(ContentType.JSON) // same as .header("Content-Type", "application/json")
            .setBasePath("/api/users/{id}")
            .build();

    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectResponseTime(lessThan(5000L))
            .expectBody("$", hasKey("data"))
            .build();

    @Test
    public void restGet() {
        given()
                .spec(requestSpec)
                .pathParam("id", 2)
                .when()
                .get()
                .then()
                .statusCode(200)
                .and()
                .body("data.first_name", equalTo("Janet"))
                .and()
                .body("data.last_name", equalTo("Weaver"));
    }

    @Test
    public void restGet2() {
        given()
                .spec(requestSpec)
                .pathParam("id", 3)
                .when()
                .get()
                .then()
                .spec(responseSpec)
                .and()
                .body("data.first_name", equalTo("Emma"))
                .and()
                .body("data.last_name", equalTo("Wong"));
    }
}
