# language: en

Feature: assets test sample

  Scenario: I wants to manual create the snmp devices as asset
    Given I have a http request body from body.json
    When I calls "CreateConnectInfoAPI" with "POST" http request
    Then status code should be 200
    And "code" in response body is "00000"
    And response body should match the schema CreateConnectInfoAPIResponse.json
    And I calls "DeleteConnectAPI" with "POST" http request with "result" field list