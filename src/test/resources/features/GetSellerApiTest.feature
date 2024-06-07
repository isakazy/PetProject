Feature: get seller verify the response is correct

  @getSingleSeller
  Scenario: get a single seller and verify the name is as expected
    Given get single seller api is hit "/api/myaccount/sellers/{id}"
    Then  verify the status code is 200
    And verify seller name is "Isakazy"