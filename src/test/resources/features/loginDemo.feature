Feature: test log in functionality

  @loginDemo
  Scenario: check log in is successful with valid credentials
    Given user is on sauce demo login page
    When user provides a valid username
    And user provides valid password
    And user clicks on login button
    Then verify user successfully logged in


@loginInvalid
Scenario: user logs in with invalid credentials
  Given user is on sauce demo login page
  When user provides a invalid username
  And user provides invalid password
  And user clicks on login button
  Then verify user sees an error message