package cn.qa.sean.steps;

import cn.qa.sean.constants.APIResources;
import cn.qa.sean.utils.JsonParser;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RequiredArgsConstructor
public class CommonSteps {
    private final JsonParser jsonParser;
    @Getter
    RequestSpecification requestSpecification;
    @Getter
    Response response;

    /**
     * for json request
     */
    @Given("I have a http request body from {word}")
    public void getHttpRequestBodyTemplateFrom(String relativeFilePath) {
        var requestBody = jsonParser.parseHttpRequestBodyJsonFromFile(relativeFilePath);
        System.out.println(requestBody);
        requestSpecification = given()
                .log().all()
                .body(requestBody);
    }

    /**
     * for set request header
     */
    @Given("I set request headers")
    public void i_set_request_headers(DataTable table) {
        requestSpecification = given().with().headers(
                table.entries().stream().collect(Collectors.toMap(
                        columns -> columns.get("header_key"),
                        columns -> columns.get("header_value"))
                )
        );
    }

    /**
     * for set request params
     */
    @Given("I set request parameters")
    public void i_set_request_parameters(DataTable table) {
        requestSpecification = given().with().params(
                table.entries().stream().collect(Collectors.toMap(
                        columns -> columns.get("param_key"),
                        columns -> columns.get("param_value"))
                )
        );
    }

    /**
     * for set request path params
     */
    @Given("I set request path params")
    public void iSetRequestPathParams(DataTable table) {
        requestSpecification = given().with().pathParams(
                table.entries().stream().collect(Collectors.toMap(
                        columns -> columns.get("param_key"),
                        columns -> columns.get("param_value"))
                )
        );
    }

    /**
     * for set request query params
     */
    @Given("I set request query params")
    public void iSetRequestQueryParams(DataTable table) {
        requestSpecification = given().with().queryParams(
                table.entries().stream().collect(Collectors.toMap(
                        columns -> columns.get("param_key"),
                        columns -> columns.get("param_value"))
                )
        );
    }

    /**
     * for set request form params
     */
    @Given("I set request form params")
    public void iSetRequestFormParams(DataTable table) {
        requestSpecification = given().with().formParams(
                table.entries().stream().collect(Collectors.toMap(
                        columns -> columns.get("param_key"),
                        columns -> columns.get("param_value"))
                )
        );
    }

    /**
     * for request with method and endpoint
     */
    @When("I calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String resource, String httpMethod) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        response = requestSpecification
                .when()
                .log().all()
                .request(httpMethod, resourceAPI.getResource());
    }

    @Then("status code should be {int}")
    public void theAPICallIsSuccessWithStatusCode(Integer statusCode) {
        response.then()
                .assertThat()
                .statusCode(statusCode);
    }

    @Then("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String expectedValue) {
        var actualValue = response.jsonPath().getString(keyValue);
        assertThat(actualValue)
                .describedAs("The value of " + keyValue + " is not " + expectedValue)
                .isEqualTo(expectedValue);
    }

    @Then("the response should contain")
    public void theResponseShouldContain(DataTable table) {
        table.asMaps().forEach(map -> {
            String jsonPath = map.get("jsonPath").toString();
            String value = map.get("value").toString();
            assertThat(response.jsonPath().getString(jsonPath))
                    .describedAs("The value of " + jsonPath + " is " + value + "incorrect")
                    .isEqualTo(value);
        });
    }

    @Then("the response should contain {string} field")
    public void theResponseShouldContain(String jsonPath) {
        assertThat(response.jsonPath().getString(jsonPath))
                .describedAs("The value of " + jsonPath + " is incorrect")
                .isNotNull();
    }

    @Then("the response should not contain {string} field")
    public void theResponseShouldNotContain(String jsonPath) {
        assertThat(response.jsonPath().getString(jsonPath))
                .describedAs("The value of " + jsonPath + " is incorrect")
                .isNull();
    }

    @Then("response body should match the schema {word}")
    public void responseBodyShouldMatchTheSchema(String schemaFile) {
        var schema = jsonParser.parseResponseSchemaJsonFromFile(schemaFile);
        response.then().assertThat().body(matchesJsonSchema(String.valueOf(schema)));
    }
}
