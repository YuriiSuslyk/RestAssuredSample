import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

public class Test {

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/")
            .setContentType(ContentType.JSON)
            .setBasePath("/api/users")
            .build();

    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectResponseTime(lessThan(5000L))
            .build();



    public void restGet2() {
        given()
                .spec(requestSpec)
                .pathParam("id", 3)
                .when()
                .get()
                .then()
                .spec(responseSpec)
                .and()
                .body("email\" :\"michael.lawson@reqres.in", equalTo("Emma"))
                .and()
                .body("Michael", equalTo("Wong"));
    }
    }