
Feature: Validating Place API's


  Scenario: Verify if place is added using AddPlace API
    Given Add Place Payload
    When User call "AddPlaceAPI" with  Post Http request
    Then Api call should be success with 200 Status code
    And "Status" in response body is "OK"
    And "Scope" in response body is "APP"
    

 