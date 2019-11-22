package apiFlow;

import init.RestAssuredExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;

@ExtendWith(RestAssuredExtension.class)
public class Run2Test {

    @Test
    public void firstDemo() {
        given().
                pathParam("username", "chengciaqiucao").
                when().
                get("/users/{username}").
                then().
                log();
    }
}
