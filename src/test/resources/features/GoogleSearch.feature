Feature: feature to test google search functionality
e


  @google
  Scenario: google search is working
    Given browser is open
    And user is on google search page
    When user provides search input
    And user hits search
    Then verify user navigated to search results




