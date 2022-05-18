# language: en

Feature: assets test

  Scenario: I wants to create an asset
    Given I have a http request body from snmp_ruijie.json
    When I calls "CreateConnectInfoAPI" with "POST" http request
    Then status code should be 200
    And "code" in response body is "00000"
    And response body should match the schema CreateConnectInfoAPIResponse.json