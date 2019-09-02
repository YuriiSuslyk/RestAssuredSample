import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

public class RestAssuredSpecPost {

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/")
            .setContentType(ContentType.JSON) // same as .header("Content-Type", "application/json")
            .setBasePath("/api/users")
            .build();

    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectResponseTime(lessThan(5000L))
            .expectBody("$", hasKey("name"))
            .expectBody("$", hasKey("job"))
            .build();

    @Test
    public void restPost() {
        given()
                .spec(requestSpec)
                .body("{\"name\": \"Oleh\", \"job\": \"Automation\"}")
                .post()
                .then()
                .spec(responseSpec)
                .and()
                .body("name", equalTo("Oleh"))
                .and()
                .body("job", equalTo("Automation"));
    }
}
