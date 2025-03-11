package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GithubResourceTest {
    @Test
    void testSummaryEndpoint() {
        given()
                .when().get("/github/repositories/bartoszOlewinski")
                .then()
                .statusCode(200);
    }
}