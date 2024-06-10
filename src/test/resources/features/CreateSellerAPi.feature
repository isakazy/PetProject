Feature: create a seller and verify

  @modifySellerEmail @regression
  Scenario: Get a single seller, change email and verify email was changed
    Given User hits get single seller api "/api/myaccount/sellers/"
    Then verify email is not empty
    And hit put api with "/api/myaccount/sellers/" and change an email to "whateverEmail@gmail.com"
    Then verify user email is as expected


  @CreateGetDeleteSeller @regression
  Scenario: Create a seller, verify seller was created and delete the same seller
    Given user hits create seller api with "/api/myaccount/sellers" 
    Then  verify user id is generated 
    And get single seller api is hit with "/api/myaccount/sellers/"
    Then verify seller name is not empty
    And verify seller email is not empty
    Then delete seller api is hit with "/api/myaccount/sellers/"
    Then get all sellers api is hit with "/api/myaccount/sellers"
    Then verify deleted seller is not present in the list



@GetAllSellers @regression
Scenario: get all sellers and verify email is not empty
  Given user hits get all sellrs api with "/api/myaccount/sellers"
  Then Verify email is not emty



  @ArchiveSeller @regression
  Scenario: get as seller, archive a seller and verify the seller was archived
    Given user hit get a single seller api with "/api/myaccount/sellers/"
    And user hits archive the seller with "/api/myaccount/sellers/archive/unarchive"
    Then hit get all sellers and verify the seller was archived "/api/myaccount/sellers"