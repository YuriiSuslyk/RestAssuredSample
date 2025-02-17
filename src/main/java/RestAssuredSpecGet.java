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
            .setBasePath("/api/users")
            .build();

    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectResponseTime(lessThan(5000L))
            .build();

    @Test
    public void restGet() {
        given()
                .spec(requestSpec)
                .pathParam("id", 2)
                .when()
                .get()
                .then()
                .spec(responseSpec)
                .body("email" :"michael.lawson@reqres.in"), equalTo("Michael"))
                .and
                .body("Lawson", equalTo("Weaver"));
    }

    @Test
    public void restGet2() {
        given()
                .spec(requestSpec)
                .param("page",2)
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
