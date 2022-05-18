package cn.qa.sean.hooks;

import cn.qa.sean.configuration.YAMLConfig;
import io.cucumber.java.*;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import io.restassured.builder.RequestSpecBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

@Slf4j
public class Hooks {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @Autowired
    private YAMLConfig yamlConfig;

    @Before
    public void setupSpecBuilder(){
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(yamlConfig.getApplication().get("url"))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", getToken())
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .addFilter (new RequestLoggingFilter())
                .addFilter (new ResponseLoggingFilter())
                .build ();
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build ();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    private String getToken() {
        Response response =
                given()
                        .baseUri(yamlConfig.getApplication().get("url"))
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("account", yamlConfig.getUsers().get("root").getUsername())
                        .formParam("password", yamlConfig.getUsers().get("root").getPassword())
                        .when()
                        .post("/auth-ui/v1/api/user/token/getToken")
                        .then()
                        .log().all()
                        .assertThat().statusCode(200).extract().response();
        System.out.println(response.jsonPath().getString("result.value"));
        return response.jsonPath().getString("result.value");
    }


    @After
    public void after(Scenario scenario) {
        System.out.println("After scenario: " + scenario.getName());
    }
    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before all");
        log.info("Test Start");
    }
    @AfterAll
    public static void afterAll() {
        System.out.println("After all");
        log.info("Test End");
    }
}
