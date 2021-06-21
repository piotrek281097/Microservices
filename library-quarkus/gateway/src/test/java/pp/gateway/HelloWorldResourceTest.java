package pp.gateway;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class HelloWorldResourceTest {

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
