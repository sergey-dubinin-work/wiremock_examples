package dubinin.sergey.test;

import dubinin.sergey.model.UserModel;
import dubinin.sergey.wiremock.PrimitiveUserMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PrimitiveUserMockServerTest {

    PrimitiveUserMockServer primitiveUserMockServer = new PrimitiveUserMockServer();

    @BeforeEach
    void setUp() {
        primitiveUserMockServer.start();
    }

    @Test
    void primitiveUserMockServerReturnsData() {
        RestAssured.baseURI = "http://localhost:8080";

        Response response = given()
                .when()
                .get("/api/users/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        UserModel userResponseBody = response.as(UserModel.class);

        assertAll(
                () -> assertThat(userResponseBody).isNotNull(),
                () -> assertThat(userResponseBody.getName()).isEqualTo("Sergey"),
                () -> assertThat(userResponseBody.getAge()).isEqualTo(31)
        );

    }

    @AfterEach
    void tearDown() {
        primitiveUserMockServer.stop();
    }
}
