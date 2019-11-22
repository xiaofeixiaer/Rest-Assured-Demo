package apiFlow;

import com.init.RestAssuredExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static utils.ExecuteSql.runSql;

@ExtendWith(RestAssuredExtension.class)
public class RunTest {

    @Test
    public void firstDemo() {
        given().
                pathParam("username", "chengciaqiucao").
                when().
                get("/users/{username}").
                then().
                log();
    }

    @Test
    public void should_test() {
        System.out.println("helooddddddd");
        runSql("insert into name (first_name) values ('yy');");
    }
}
