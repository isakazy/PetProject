Feature: create a seller and verify

  @CreateGetDeleteSeller
  Scenario: Create a seller, verify seller was created and delete the same seller
    Given user hits create seller api with "/api/myaccount/sellers" 
    Then  verify user id is generated 
    And get single seller api is hit with "/api/myaccount/sellers/"
    Then verify seller name is not empty
    And verify seller email is not empty
    Then delete seller api is hit with "/api/myaccount/sellers/"
    Then get all sellers api is hit with "/api/myaccount/sellers"
    Then verify deleted seller is not present in the list