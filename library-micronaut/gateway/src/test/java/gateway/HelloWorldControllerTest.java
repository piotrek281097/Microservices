package gateway;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@MicronautTest
public class HelloWorldControllerTest {

    @Test
    public void testHelloWorld() {
        given()
            .port(8081)
            .when().get("/test")
            .then()
                .statusCode(200)
                .body(is("hello world"));
    }

}
