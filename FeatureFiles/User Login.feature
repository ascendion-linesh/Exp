Feature: User Login
  As a registered user,
  I want to log in to my account,
  So that I can access personalized features.

  Scenario: Login with valid credentials
    Given the user has a registered account
    And the user is on the home page
    When the user clicks 'Sign In'
    And enters a valid email or mobile number and password
    And clicks on the 'Login' button
    Then the user should be logged in and redirected to the home page

  Scenario: Login with invalid credentials
    Given the user is on the home page
    When the user clicks 'Sign In'
    And enters an invalid email or mobile number or password
    And clicks on the 'Login' button
    Then an error message should be displayed
